# TDD로 다시 보는 로또

## 🎯 TDD가 제공한 실질적 이점

## 1. 요구사항 탐색 & 설계 피드백 도구로서의 TDD

### Rank(등수) 설계에 대한 첫 접근

#### 요구사항

- 6개 일치 → 1등 (2,000,000,000원)
- 5개 + 보너스 → 2등 (30,000,000원)
- 5개 일치 → 3등 (1,500,000원)
- 4개 일치 → 4등 (50,000원)
- 3개 일치 → 5등 (5,000원)
- 그 외 → 낙첨

#### 테스트 먼저 작성

```java

@Test
void _6개_일치하면_1등이다() {
    Rank rank = Rank.of(6, false);

    assertThat(rank).isEqualTo(Rank.FIRST);
}
```

- '6개 일치하고 보너스는 상관없으면 1등'은 명확하다!
- `Rank.of(matchCount, hasBonus)` 형태의 API가 자연스럽게 도출

---

### 테스트 작성 중 요구사항을 자세하게 이해하며 추가 설계 요소 발견

- 일치 개수랑 보너스 여부로 등수가 결정된다!
- 각 등수마다 상금도 있으니 **Enum으로 관리**하면 좋겠다!

#### 설계 고민

| 질문            | 답변                    |
|---------------|-----------------------|
| 각 등수는 뭘 가지지?  | 일치 개수, 상금, 보너스 필요 여부  |
| 등수는 어떻게 판단하지? | 일치 개수와 보너스만으로 결정      |
| 낙첨은?          | null? Optional? NONE? |

#### 낙첨 표현 방식 결정

| 방식       | 장점       | 단점                      |
|----------|----------|-------------------------|
| null     | 단순함      | NullPointerException 위험 |
| Optional | 명시적이고 안전 | 코드가 길어짐                 |
| NONE     | enum 일관성 | 의미가 모호                  |

**→ Optional 사용으로 결정!**

- null 체크보다 안전
- 등수가 없을 수 있다는 의미가 명확
- 스트림과 잘 어울림

---

### 테스트로 보너스 번호 로직에 대한 정확한 이해 및 버그 사전 발견

```java

@Test
void _6개_일치는_보너스_상관없이_1등이다() {
    assertThat(Rank.of(6, true).get()).isEqualTo(Rank.FIRST);
    assertThat(Rank.of(6, false).get()).isEqualTo(Rank.FIRST);
}
```

#### 버그 발견!

- **Red 사이클에서 버그를 미리 사전에 발견!**
- **실제 요구사항:** "1등, 3등, 4등, 5등은 보너스와 무관하게 판단해야 한다"

---

### 배운 점

#### 요구사항을 테스트로 명확화

- "낙첨을 어떻게 표현하지?"라는 모호한 요구사항이 테스트 작성 과정에서 Optional로 구체화됨
- 테스트를 먼저 작성했기에 구현 전에 "결과가 없을 수 있다"는 케이스를 사전 인식

#### 테스트 작성과 요구사항 이해를 통해 엣지 케이스 버그 사전 발견

- TDD를 통해 테스트를 먼저 작성하고 동작에 대해 고민하며 요구사항을 자세하게 이해하지 않았다면, **숨은 보너스 로직 버그를 인식조차 못했을 수도 있다**
- 모든 경우의 수를 테스트함으로써 순서 의존적인 로직의 정확성 검증

#### 설계 개선의 자연스러운 흐름

| 단계 | 구현 방식       | 특징              |
|----|-------------|-----------------|
| 1차 | 단순 if문 나열   | 조건이 분산됨         |
| 2차 | enum에 조건 저장 | 조건이 응집됨         |
| 3차 | matches 메서드 | 각 등수가 자신의 조건 판단 |

> **TDD를 통해 살아있는 테스트가 있었기에 점진적으로 정확한 로직을 구현하며 리팩토링을 과감하게 진행할 수 있었음**

#### TDD의 실질적 이점 체감

- "이렇게 동작하겠지" → 잘못된 가정
- "테스트로 먼저 꼼꼼히 확인해보자" → 확실한 검증
- 작은 단위로 검증하며 진행하므로 **큰 실수를 조기에 발견**

---

### 정렬 책임 결정 - 생성 시 vs 출력 시

```java

@Test
void 로또_번호는_오름차순으로_정렬된다() {
    Lotto lotto = new Lotto(List.of(6, 3, 1, 5, 2, 4));

    assertThat(lotto.getNumbers())
            .containsExactly(1, 2, 3, 4, 5, 6);
}
```

요구사항에 '오름차순으로 정렬하여 보여준다'고 했는데, **"정렬은 언제 해야 하지?"**

| 선택지         | 장점                                       | 단점                   |
|-------------|------------------------------------------|----------------------|
| **생성 시 정렬** | 내부 상태가 항상 정렬, 호출마다 정렬 불필요, "항상 정렬" 불변 조건 | -                    |
| **출력 시 정렬** | 입력 순서 유지, 다양한 정렬 방식 가능                   | 출력마다 중복 정렬, 잊어버릴 가능성 |

#### 실제 사용 시나리오 고려

- 실제 현실에서 대부분 모든 로또들은 **정렬된 채로 존재**하며 처음 생성 시 정렬됨
- 출력마다 정렬할 경우 **중복 작업** + **잊어버릴 가능성**

**→ 생성 시 정렬로 결정!**

#### 배운 점

- 단순히 "동작하는가"가 아니라 **"실제 여러 곳에서 어떻게 쓰일까"** 고민
- 1차원적으로 보여지는 출력의 책임이라 단정 짓지 않을 수 있다
- **TDD가 이런 사고를 자연스럽게 유도**

---

## 2. 설계 피드백 도구 & 리팩토링 안전망으로서의 TDD

### Lotto 설계 시 LottoNumber 재사용 설계 발견

#### 테스트 먼저 작성

```java

@Test
void 로또_번호는_1부터_45_사이여야_한다() {
    assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1부터 45");

    assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("1부터 45");
}
```

#### 초기 구현

```java
public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);  // 범위 검증 추가!
        this.numbers = numbers;
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                throw new IllegalArgumentException(
                        "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."
                );
            }
        }
    }
}
```

#### 리팩토링 단계에서 중복 발견!

- **LottoNumber에서 이미 테스트하고 구현했던 로직임을 인식**
    - 에러 메시지 동일
    - 조건문 로직 동일
- 같은 로직이 두 곳에 존재하니 **변경 시 두 곳을 수정해야 함!**
- **책임이 분산됨!**

#### 설계 개선

```java
public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        this.numbers = convertToLottoNumbers(numbers);
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }
}
```

- Lotto가 `List<Integer>` 대신 `List<LottoNumber>`를 가지면 해결!
- 생성 시점에 자동으로 검증됨
- **리팩토링 후 기존 테스트들도 모두 잘 동작함을 확인!**

#### 배운 점

- **테스트 후 리팩토링 과정에서 중복을 발견하고 개선 설계 포인트 발견!**
    - TDD 사이클이 아니었으면 그저 잘 동작한다는 것만 확인하고 중복 인식 자체를 안 할 가능성 높음
    - 점차 이런 책임이 분산된 로직들이 쌓여 리팩토링과 테스트 코드의 발전이 멈춤
- **테스트가 리팩토링 안전망 역할**
    - TDD의 살아있는 테스트 코드 덕분에 과감하게 리팩토링 가능
    - 심리적 안정감과 개발 방향성 몰입을 시스템적으로 지원
- **TDD의 누적 효과**
    - 작은 객체를 잘 만들면 조합을 통해 각 객체가 더욱 자기 책임만 완수하면 되는 구조가 됨

---

### WinningLotto 객체간 협력 설계

TDD로 WinningLotto를 개발하기 전 이미 LottoNumber, Lotto, Rank와 같은 핵심 객체들이 완성된 상태.

- `LottoNumber` → 번호 값 + 동등성 비교
- `Lotto` → 번호 보유 + 일치 개수 계산
- `Rank` → 등수 판단 로직 담당

#### 테스트 먼저 작성

```java

@Test
void 로또와_비교하여_등수를_판단한다_1등() {
    // given
    WinningLotto winning = new WinningLotto(
            List.of(1, 2, 3, 4, 5, 6), 7);

    Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    // when
    Rank rank = winning.match(lotto);

    // then
    assertThat(rank).isEqualTo(Rank.FIRST);
}
```

#### API 설계 고민

| 질문            | 답변                       |
|---------------|--------------------------|
| match는 누가 호출? | 당첨 번호가 로또를 검사한다가 자연스럽다   |
| 반환 타입은?       | Rank                     |
| 파라미터는?        | Lotto만 받으면 됨. 보너스는 이미 소유 |

#### 구현 방식 결정

| 방식                      | 문제점                                              |
|-------------------------|--------------------------------------------------|
| WinningLotto가 직접 계산     | Lotto의 getter를 써서 내부를 너무 많이 알게 됨 → 캡슐화 깨짐        |
| **기존 객체들에게 필요한 정보만 요구** | 로또에게 가지고 있는지 물어보고 개수 받기, Rank에게 해당 개수 전달해서 결과 받기 |

#### 각 객체의 역할과 책임

| 객체               | 역할             | 설명                                          |
|------------------|----------------|---------------------------------------------|
| **WinningLotto** | 조정자            | 다른 객체에게 일을 시켜 결과만 조합. 직접 계산하지 않음            |
| **Lotto**        | 자신의 번호에 대한 전문가 | 자신의 번호를 알고 있어, 다른 번호들과의 비교 방법을 앎. 내부 구조는 숨김 |
| **Rank**         | 등수 판단 전문가      | 등수 판단 규칙을 알고 있으며 변환도 함                      |

#### 배운 점

- **테스트가 메시지를 발견** - `match`라는 메시지가 필요하고 반환 타입과 파라미터 결정
- **구현하며 협력자 발견 및 기존 객체 재사용**
    - 일치 개수는 Lotto가 알고 있다
    - 보너스 일치 여부도 Lotto가 알고 있다
    - Rank는 등수를 결정하고 반환 결과를 줄 수 있다
- **객체가 잘 만들어져 있으면, 나중에는 조합만 하면 된다**
- **TDD는 협력 설계를 자연스럽게 발견하게 한다**

---

## 3. 리팩토링 안전망으로서의 TDD

### Controller 책임 분리 및 DTO 도입 리팩토링

#### 개선 리팩토링 사항 인식

**컨트롤러가 너무 많은 책임을 가지고 있다**

- 통계 수집 로직 포함 (for문으로 직접 순회하며 `statistics.add()` 직접 호출)
- 출력 순서 제어 (`Rank.values()` 순회하며 `isWinning()` 필터링)

**View가 Rank 도메인에 의존**

- Rank가 이미 알고 있는 몇 가지 정보가 있지만 중복 로직 존재
- Rank 변경 시 View도 수정 필요 → 결합도가 높아 변경 영향 범위가 큼

> **⇒ DTO를 통해 개선할 수 있음을 파악!**

---

#### Rank에 표현 정보 추가

```java

@Test
void Rank는_설명을_제공한다() {
    assertThat(Rank.FIRST.getDescription()).isEqualTo("6개 일치");
    assertThat(Rank.SECOND.getDescription()).isEqualTo("5개 일치, 보너스 볼 일치");
    assertThat(Rank.THIRD.getDescription()).isEqualTo("5개 일치");
}

@Test
void 출력_순서대로_당첨_등수를_반환한다() {
    List<Rank> ranks = Rank.getWinningRanksInDisplayOrder();

    // 5등부터 1등 순서
    assertThat(ranks).containsExactly(
            Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
    );
}
```

```java
public enum Rank {
    FIRST(6, 2_000_000_000, false, "6개 일치"),
    SECOND(5, 30_000_000, true, "5개 일치, 보너스 볼 일치"),
    ...
    private final String description;

    ...

    public static List<Rank> getWinningRanksInDisplayOrder() {
        return List.of(FIFTH, FOURTH, THIRD, SECOND, FIRST);
    }
}
```

---

#### RankStatistic DTO 생성

- **View는 이제 RankStatistic만 알면 된다!**
- **Rank의 내부 구조 몰라도 됨!**

---

#### 개선 전후 비교

| 항목                  | Before            | After                      |
|---------------------|-------------------|----------------------------|
| **Controller**      | 통계 수집 + 출력 순서 제어  | 단순 조립만                     |
| **LottoStatistics** | 계산만               | 통계 수집 + DTO 생성             |
| **OutputView**      | Rank 지식 필요 (하드코딩) | 순수 출력만 (DTO 사용)            |
| **purchaseAmount**  | 필드로 보관            | 파라미터로 전달                   |
| **DTO**             | 없음                | RankStatistic, LottoResult |
| **View-Domain 결합**  | 강함                | DTO로 분리                    |

---

#### 배운 점

**TDD 사이클의 살아있는 테스트가 리팩토링의 안전망**

점진적 리팩토링 가능:

1. Rank 수정 → 테스트 통과 확인
2. DTO 추가 → 테스트 통과 확인
3. Statistics 수정 → 테스트 통과 확인
4. Controller 수정 → 테스트 통과 확인
5. View 수정 → 테스트 통과 확인

> **대규모 리팩토링을 진행하며 기존 동작 테스트 이상 없음을 확인**

**DTO의 역할**

- View와 Domain 분리
- 결합도 감소로 인해 변경 영향 최소화
- 테스트 용이

---

## 🤔 어떤 고민이 있었고, 왜 그런 결정을 했는지

---

## Service 계층 도입과 철회 - 과도한 설계의 발견

### 문제 인식

```java
public void run() {
    Money purchaseAmount = readPurchaseAmountWithRetry();
    List<Lotto> lottos = issueLottos(purchaseAmount);
    WinningLotto winningLotto = createWinningLottoWithRetry();

    LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);

    List<RankStatistic> rankStats = statistics.getRankStatistics();
    double profitRate = calculateProfitRate(statistics, purchaseAmount);
    LottoResult result = LottoResult.of(rankStats, profitRate);

    outputView.printResult(result);
}
```

DTO 도입 후 컨트롤러에서 DTO 변환을 하며 **여전히 무겁다는 걸 인식**

- DTO 변환 로직이 Controller에 있는 게 맞나?
- "Domain → DTO 변환은 어디서 해야 하지?"
- 계층 분리가 더 필요한가?

---

### Service 계층 도입

```java
public class LottoResultService {

    public LottoResult createResult(LottoStatistics statistics, int purchaseAmount) {
        List<RankStatistic> rankStats = statistics.getRankStatistics();

        ProfitRate profitRate = ProfitRate.calculate(
                statistics.getTotalPrize(),
                purchaseAmount
        );

        return LottoResult.of(rankStats, profitRate.getValue());
    }
}
```

**Service의 책임**

- 등수별 통계 DTO 생성
- 수익률 계산
- DTO 조립 변환 후 반환

```java
public class LottoController {
    private final LottoResultService resultService;

    public void run() {
        // ...
        LottoStatistics statistics = LottoStatistics.from(lottos, winningLotto);
        LottoResult result = resultService.createResult(statistics, purchaseAmount);
        outputView.printResult(result);
    }
}
```

**기대했던 장점**

- Domain 순수성 유지 (DTO 의존 제거)
- 책임 명확 분리
- 변환 로직의 명확한 위치
- 계층 구조 확립

---

### 의문 발생 및 Service 본래 역할 분석

> **"Service가 하는 일이 DTO 변환뿐인데??"**
> **"비즈니스 로직이 없는데?"**
> **"이게 Service 계층의 역할이 맞나?"**

#### Service의 진짜 역할

| 역할              | 설명                   |
|-----------------|----------------------|
| 여러 Domain 객체 조율 | 복잡한 도메인 간 협력 관리      |
| 트랜잭션 경계 관리      | DB 작업의 원자성 보장        |
| 외부 서비스 호출       | API, 메시징 등 외부 시스템 연동 |
| Repository 접근   | 데이터 저장/조회            |
| 복잡한 비즈니스 흐름 관리  | 여러 단계의 비즈니스 로직 조율    |

---

### 또 다시 TDD와 OOP 간의 모순 발견

| TDD 원칙         | OOP 학습 목표          |
|----------------|--------------------|
| 필요한 것만 만든다     | 유지보수성이 좋아지도록 설계 적용 |
| 과도한 설계를 하지 않는다 | 확장 가능한 구조 추가       |
| 현재 요구사항에 집중한다  | 계층 분리 극대화          |

> **⇒ 미래를 위한 과도한 설계를 한 걸 인식**

---

### 현재 프로젝트 상황 분석

**프로젝트 특성**

- 단순 콘솔 기능만 있다
- DB 없음
- 외부 시스템 없음
- Domain 간 복잡한 조율 없음

**현재의 LottoResultService가 하는 일**

- DTO 변환
- 수익률 계산
- 결과 조립

> **⇒ 이건 Service가 아니라 Mapper/Converter의 역할!**

---

### 최종 결정: Service 계층 철회

현재 프로젝트 규모와 복잡도에서는 Service 계층이 **불필요한 추상화**임을 인식하고 철회

---

### 배운 점
- Service의 진짜 역할
    - 비즈니스 로직 조율자
    - 트랜잭션 경계 관리
    - 외부 시스템 통합점
    - 복잡한 흐름 관리자
- 과도한 설계 인식
    - 계층과 책임이 많이 분리 된다고 좋은게 아니라 오히려 독이다
    - 프로젝트 규모와 상황에 맞는 설계가 좋은 설계이다.
- 리팩토링은 목적이 아니라 수단
    - 실제 문제 해결을 위해 리팩토링
    - 필요한 만큼만 개선
    - 해당 상황에 맞는 적절한 설계
