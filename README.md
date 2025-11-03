# 📚 3주차 미션 - 로또

## 🎯 유즈케이스 (Use Cases)

### UC-01: 로또 구입 금액 입력

**목적:** 로또 구입에 사용할 금액을 입력받는다.  
**사전조건:** 게임이 시작된다.  
**사후조건:** 유효한 구입 금액이 설정되고 구입할 로또 개수가 결정된다.

**주 시나리오:**

1. 시스템이 구입 금액 입력을 요청한다.
2. 사용자가 구입 금액을 입력한다.
3. 시스템이 입력값을 검증한다.
4. 시스템이 구입 금액을 1,000으로 나누어 로또 개수를 계산한다.

**예외 시나리오:**

- a. 금액이 1,000원 단위가 아닌 경우
- b. 금액이 1000원 이하인 경우
- c. 숫자가 아닌 값을 입력한 경우

**입력 예시:**

```
14000
```

---

### UC-02: 로또 발행

**목적:** 구입 금액에 해당하는 로또를 자동으로 발행한다.  
**사전조건:** 유효한 구입 금액이 입력되었다.  
**사후조건:** 구입 개수만큼 로또가 생성되고 번호가 출력된다.

**주 시나리오:**

1. 시스템이 구입 개수만큼 반복한다.
    - 1.1. 1~45 범위에서 중복되지 않는 6개의 숫자를 생성한다.
    - 1.2. 생성된 숫자로 로또 객체를 생성한다.
    - 1.3. 생성된 로또를 목록에 추가한다.
2. 시스템이 구입한 로또 개수를 출력한다.
3. 시스템이 각 로또의 번호를 오름차순으로 정렬하여 출력한다.

**예외 시나리오:**

- a. 로또 번호가 6개가 아닌 경우
- b. 로또 번호에 중복이 있는 경우
- c. 로또 번호가 1~45 범위를 벗어난 경우

**출력 예시:**

```
8개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[1, 3, 5, 14, 22, 45]
```

---

### UC-03: 당첨 번호 입력

**목적:** 당첨 번호 6개를 입력받는다.  
**사전조건:** 로또가 발행되었다.  
**사후조건:** 유효한 당첨 번호가 설정된다.

**주 시나리오:**

1. 시스템이 당첨 번호 입력을 요청한다.
2. 사용자가 쉼표(`,`)로 구분된 6개의 번호를 입력한다.
3. 시스템이 입력값을 파싱한다.
4. 시스템이 당첨 번호를 검증한다.
5. 시스템이 당첨 번호 객체를 생성한다.

**예외 시나리오:**

- a. 번호가 6개가 아닌 경우
- b. 번호가 1~45 범위를 벗어난 경우
- c. 중복된 번호가 있는 경우
- d. 숫자가 아닌 값이 포함된 경우
- e. 쉼표로 구분되지 않은 경우
- f. 연속된 쉼표가 있는 경우

**입력 예시:**

```
1,2,3,4,5,6
```

---

### UC-04: 보너스 번호 입력

**목적:** 보너스 번호 1개를 입력받는다.  
**사전조건:** 당첨 번호가 입력되었다.  
**사후조건:** 유효한 보너스 번호가 설정된다.

**주 시나리오:**

1. 시스템이 보너스 번호 입력을 요청한다.
2. 사용자가 보너스 번호를 입력한다.
3. 시스템이 입력값을 검증한다.
4. 시스템이 보너스 번호를 당첨 번호 객체에 설정한다.

**예외 시나리오:**

- a. 숫자가 아닌 값을 입력한 경우
- b. 번호가 1~45 범위를 벗어난 경우
- c. 당첨 번호와 중복되는 경우

**입력 예시:**

```
7
```

---

### UC-05: 당첨 내역 확인

**목적:** 구매한 로또와 당첨 번호를 비교하여 당첨 내역을 확인한다.  
**사전조건:**

- 로또가 발행되었다.
- 당첨 번호와 보너스 번호가 입력되었다.

**사후조건:**

- 등수별 당첨 개수가 계산된다.
- 당첨 통계가 출력된다.

**주 시나리오:**

1. **[각 로또에 대해 반복]**
    - 1.1. 당첨 번호와 일치하는 번호 개수를 센다.
    - 1.2. 보너스 번호 일치 여부를 확인한다.
    - 1.3. 일치 개수와 보너스 여부로 등수를 판정한다.
    - 1.4. 판정된 등수를 통계에 기록한다.
2. 시스템이 당첨 통계 헤더를 출력한다.
3. 시스템이 5등부터 1등까지 순서대로 당첨 내역을 출력한다.

**당첨 기준:**

- 1등: 6개 번호 일치 / 2,000,000,000원
- 2등: 5개 번호 + 보너스 번호 일치 / 30,000,000원
- 3등: 5개 번호 일치 / 1,500,000원
- 4등: 4개 번호 일치 / 50,000원
- 5등: 3개 번호 일치 / 5,000원
- 낙첨: 2개 이하 일치

**출력 예시:**

```
당첨 통계
---
3개 일치 (5,000원) - 1개
4개 일치 (50,000원) - 0개
5개 일치 (1,500,000원) - 0개
5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
6개 일치 (2,000,000,000원) - 0개
```

---

### UC-06: 수익률 계산 및 출력

**목적:** 총 당첨 금액을 바탕으로 수익률을 계산하여 출력한다.  
**사전조건:** 당첨 내역이 확인되었다.  
**사후조건:** 수익률이 출력되고 게임이 종료된다.

**주 시나리오:**

1. 시스템이 총 당첨 금액을 계산한다.
    - 각 등수의 당첨 개수 × 상금을 합산
2. 시스템이 수익률을 계산한다.
    - 수익률 = (총 당첨 금액 / 총 구입 금액) × 100
3. 시스템이 수익률을 소수점 둘째 자리에서 반올림한다.
4. 시스템이 수익률을 출력한다.
    - 형식: `총 수익률은 {수익률}%입니다.`

**출력 예시:**

```
총 수익률은 62.5%입니다.
```

---

## 📋 구현할 기능 목록

### 1. 입력 기능

- [x] 구입 금액 입력 받기
    - `Console.readLine()`을 사용하여 입력 받음
    - 입력 프롬프트: `"구입금액을 입력해 주세요."`
- [x] 당첨 번호 입력 받기
    - `Console.readLine()`을 사용하여 입력 받음
    - 입력 프롬프트: `"당첨 번호를 입력해 주세요."`
- [x] 보너스 번호 입력 받기
    - `Console.readLine()`을 사용하여 입력 받음
    - 입력 프롬프트: `"보너스 번호를 입력해 주세요."`

### 2. 입력 검증 기능

- [x] 구입 금액 검증
    - [x] 숫자 형식인지 확인
    - [x] 양의 정수인지 확인
    - [x] 1,000원 단위인지 확인
    - [x] 검증 실패 시 `IllegalArgumentException` 발생
- [x] 당첨 번호 검증
    - [x] 쉼표로 구분되어 있는지 확인
    - [x] 6개의 번호인지 확인
    - [x] 각 번호가 1~45 범위인지 확인
    - [x] 중복된 번호가 없는지 확인
    - [x] 검증 실패 시 `IllegalArgumentException` 발생
- [x] 보너스 번호 검증
    - [x] 숫자 형식인지 확인
    - [x] 1~45 범위인지 확인
    - [x] 당첨 번호와 중복되지 않는지 확인
    - [x] 검증 실패 시 `IllegalArgumentException` 발생

### 3. 로또 생성 기능

- [x] 로또 번호 생성
    - `Randoms.pickUniqueNumbersInRange(1, 45, 6)` 사용
    - 1~45 범위에서 중복되지 않는 6개 숫자 생성
- [x] Lotto 객체 생성
    - 생성된 번호로 Lotto 객체 생성
    - 생성 시 자동으로 번호 검증 (6개인지 확인)
- [x] 다중 로또 생성
    - 구입 금액에 따라 여러 개의 로또 생성
    - 구입 개수 = 구입 금액 / 1,000

### 4. 로또 번호 관리 기능

- [x] 로또 번호 저장
    - `List<Integer>`로 6개의 번호 저장
    - `private final`로 불변성 보장
- [x] 로또 번호 정렬
    - 출력 시 오름차순으로 정렬
- [x] 로또 번호 조회
    - 저장된 번호 목록 반환

### 5. 당첨 번호 관리 기능

- [x] 당첨 번호 저장
    - 6개의 당첨 번호 저장
- [x] 보너스 번호 저장
    - 1개의 보너스 번호 저장
- [x] 번호 포함 여부 확인
    - 특정 번호가 당첨 번호에 포함되어 있는지 확인

### 6. 당첨 확인 기능

- [x] 일치 번호 개수 계산
    - 구매한 로또와 당첨 번호를 비교
    - 일치하는 번호의 개수 반환
- [x] 보너스 번호 일치 확인
    - 구매한 로또에 보너스 번호가 포함되어 있는지 확인
- [x] 당첨 등수 판정
    - 일치 개수와 보너스 여부로 등수 결정
    - Enum을 사용하여 등수 관리 (FIRST, SECOND, THIRD, FOURTH, FIFTH, NONE)

### 7. 당첨 등수 관리 기능 (Enum)

- [x] 등수별 상금 정의
    - FIRST: 6개 일치, 2,000,000,000원
    - SECOND: 5개 + 보너스 일치, 30,000,000원
    - THIRD: 5개 일치, 1,500,000원
    - FOURTH: 4개 일치, 50,000원
    - FIFTH: 3개 일치, 5,000원
    - NONE: 낙첨, 0원
- [x] 일치 개수와 보너스 여부로 등수 반환
    - `valueOf(int matchCount, boolean matchBonus)` 메서드
- [x] 등수 설명 문자열 반환
    - `getDescription()` 메서드
- [x] 상금 금액 반환
    - `getPrizeAmount()` 메서드

### 8. 당첨 통계 기능

- [x] 등수별 당첨 개수 저장
    - `Map<Rank, Integer>` 사용
- [x] 당첨 결과 추가
    - 각 로또의 당첨 등수를 통계에 기록
- [x] 특정 등수의 당첨 개수 조회
    - `getCountByRank(Rank rank)` 메서드
- [x] 총 당첨 금액 계산
    - 각 등수의 (당첨 개수 × 상금)을 합산
- [x] 수익률 계산
    - (총 당첨 금액 / 총 구입 금액) × 100
    - 소수점 둘째 자리에서 반올림

### 9. 출력 기능

- [x] 구매한 로또 개수 출력
    - 형식: `"{개수}개를 구매했습니다."`
- [x] 각 로또 번호 출력
    - 형식: `"[{번호}, {번호}, ...]"`
    - 번호는 오름차순 정렬
- [x] 당첨 통계 헤더 출력
    - `"당첨 통계"` 출력
    - `"---"` 구분선 출력
- [x] 등수별 당첨 내역 출력
    - 5등부터 1등까지 순서대로 출력
    - 형식: `"{일치 개수}개 일치 ({상금}원) - {당첨 개수}개"`
    - 2등의 경우: `"5개 일치, 보너스 볼 일치 (30,000,000원) - {당첨 개수}개"`
- [x] 수익률 출력
    - 형식: `"총 수익률은 {수익률}%입니다."`
    - 수익률은 소수점 첫째 자리까지 표시
- [x] 에러 메시지 출력
    - 형식: `"[ERROR] {에러 내용}"`

### 10. 게임 진행 제어 기능

- [x] 게임 전체 흐름 제어
    - 구입 금액 입력 → 로또 발행 → 당첨 번호 입력 → 보너스 번호 입력 → 당첨 확인 → 수익률 출력
- [x] 예외 처리 및 재입력
    - 잘못된 입력 시 에러 메시지 출력
    - 해당 부분부터 다시 입력 받음

---

## 🧾 로또 게임 — RDD(책임-주도 설계) 관점 정리

### 1️⃣ 협력(대화) 시나리오 — 메시지 흐름

### 📍 시나리오 1: 로또 구매

| **송신자**             | **수신자**                  | **메시지**                               | **설명**                       |
|---------------------|--------------------------|---------------------------------------|------------------------------|
| 사용자                 | **InputView**            | 입력 제공                                 | 구입 금액을 입력한다.                 |
| **LottoController** | **InputHandler**         | `inputPurchaseAmount()`               | 구입 금액 입력을 요청한다.              |
| **InputHandler**    | **RetryHandler**         | `retryUntilValid(() -> ...)`          | 유효할 때까지 재시도 로직 실행            |
| **InputHandler**    | **InputView**            | `readPurchaseAmount()`                | 사용자로부터 금액 문자열을 읽는다.          |
| **InputHandler**    | **PurchaseAmount**       | `from(String input)`                  | 문자열을 구입 금액 객체로 변환 *(검증 포함)*  |
| **PurchaseAmount**  | **PurchaseAmount**       | `parseAmount(String)` *(private)*     | 문자열을 정수로 파싱                  |
| **PurchaseAmount**  | **PurchaseAmount**       | `validate(int)` *(private)*           | 1,000원 단위 검증, 최소 금액 검증       |
| **LottoController** | **LottoMachine**         | `purchase(PurchaseAmount)`            | 로또 구매를 요청한다.                 |
| **LottoMachine**    | **PurchaseAmount**       | `getLottoQuantity()`                  | 구매할 로또 개수를 조회 *(금액 / 1,000)* |
| **LottoMachine**    | **LottoNumberGenerator** | `generate()` *(n번 반복)*                | 랜덤 번호 6개 생성 요청               |
| **LottoMachine**    | **Lotto**                | `new Lotto(List<Integer>)` *(n번)*     | 생성된 번호로 로또 객체 생성             |
| **Lotto**           | **Lotto**                | `validate(List<Integer>)` *(private)* | 번호 검증 *(6개, 1~45, 중복 없음)*    |
| **LottoController** | **OutputView**           | `printPurchaseCount(int)`             | 구매한 로또 개수를 출력                |
| **LottoController** | **OutputView**           | `printLottos(List<Lotto>)`            | 각 로또 번호를 출력                  |

---

### 📍 시나리오 2: 당첨 번호 입력

| **송신자**             | **수신자**            | **메시지**                                  | **설명**                        |
|---------------------|--------------------|------------------------------------------|-------------------------------|
| **LottoController** | **InputHandler**   | `inputWinningNumbers()`                  | 당첨 번호 입력을 요청한다.               |
| **InputHandler**    | **RetryHandler**   | `retryUntilValid(() -> ...)`             | 유효할 때까지 재시도                   |
| **InputHandler**    | **InputView**      | `readWinningNumbers()`                   | 당첨 번호 문자열을 읽는다.               |
| **InputView**       | **InputValidator** | `validateInput(String)`                  | 쉼표 구분자 형식 검증                  |
| **InputView**       | **InputParser**    | `parseToStringList(String)`              | 쉼표 기준 문자열 분리                  |
| **InputHandler**    | **Lotto**          | `from(List<String>)`                     | 문자열 리스트를 로또 객체로 변환 *(정적 팩토리)* |
| **Lotto**           | **Lotto**          | `parseNumbers(List<String>)` *(private)* | 문자열을 정수 리스트로 파싱               |
| **InputHandler**    | **InputView**      | `readBonusNumber()`                      | 보너스 번호 문자열을 읽는다.              |
| **InputHandler**    | **BonusNumber**    | `of(String, Lotto)`                      | 보너스 번호 객체 생성 *(정적 팩토리)*       |
| **BonusNumber**     | **BonusNumber**    | `parseValue(String)` *(private)*         | 문자열을 정수로 파싱                   |
| **BonusNumber**     | **BonusNumber**    | `validateRange(int)` *(private)*         | 1~45 범위 검증                    |
| **BonusNumber**     | **Lotto**          | `contains(int)`                          | 당첨 번호와 중복 검증                  |
| **InputHandler**    | **WinningNumbers** | `new WinningNumbers(Lotto, BonusNumber)` | 당첨 정보 객체 생성 *(조합)*            |

---

### 📍 시나리오 3: 당첨 확인 및 수익률 계산

| **송신자**               | **수신자**               | **메시지**                                  | **설명**                         |
|-----------------------|-----------------------|------------------------------------------|--------------------------------|
| **LottoController**   | **LottoMachine**      | `calculateStatistics(WinningNumbers)`    | 당첨 통계 계산 요청                    |
| **LottoMachine**      | **WinningNumbers**    | `match(Lotto)` *(각 로또마다)*                | 구매한 로또와 당첨 번호 비교하여 등수 판정       |
| **WinningNumbers**    | **WinningNumbers**    | `countMatches(Lotto)` *(private)*        | 일치하는 번호 개수 계산                  |
| **WinningNumbers**    | **Lotto**             | `contains(int)` *(당첨 번호 확인용)*            | 특정 번호가 포함되어 있는지 확인             |
| **WinningNumbers**    | **Lotto**             | `contains(int)` *(보너스 번호 확인용)*           | 보너스 번호 일치 여부 확인                |
| **WinningNumbers**    | **Rank**              | `valueOf(int matchCount, boolean bonus)` | 일치 개수와 보너스 여부로 등수 판정           |
| **Rank**              | **Rank**              | `matches(int, boolean)` *(private)*      | 각 Rank 상수가 조건 확인 *(다형성)*       |
| **LottoMachine**      | **WinningStatistics** | `from(List<Rank>)`                       | Rank 리스트를 통계 객체로 변환 *(정적 팩토리)* |
| **LottoController**   | **WinningStatistics** | `calculateProfitRate(PurchaseAmount)`    | 수익률 계산 요청 *(통계 객체가 직접 계산)*     |
| **WinningStatistics** | **WinningStatistics** | `calculateTotalPrize()` *(private)*      | 총 당첨 금액 계산 *(내부 메서드)*          |
| **WinningStatistics** | **Rank**              | `getPrizeAmount()` *(각 등수마다)*            | 등수별 상금 조회                      |
| **WinningStatistics** | **ProfitRate**        | `of(long totalPrize, PurchaseAmount)`    | 수익률 객체 생성 *(정적 팩토리)*           |
| **ProfitRate**        | **PurchaseAmount**    | `getAmount()`                            | 구입 금액 조회                       |
| **LottoController**   | **OutputView**        | `printStatisticsHeader()`                | 통계 헤더 출력                       |
| **LottoController**   | **OutputView**        | `printStatistics(WinningStatistics)`     | 등수별 당첨 내역 출력                   |
| **OutputView**        | **WinningStatistics** | `getCountByRank(Rank)` *(각 등수마다)*        | 등수별 당첨 개수 조회                   |
| **OutputView**        | **Rank**              | `getDescription()`, `getPrizeAmount()`   | 등수 설명과 상금 조회                   |
| **LottoController**   | **OutputView**        | `printProfitRate(double)`                | 수익률 출력                         |

> 💡 협력은 요청–응답 메시지로 표현되며, 메시지가 인터페이스를 결정한다.
>
>
> 각 객체는 자율적으로 내부 메서드를 선택하여 행동한다 (**캡슐화**).
>

---

### 2️⃣ 🧩 역할 · 책임 · 메시지 (Role–Responsibility–Message)

| **역할(Role)**                   | **책임(Responsibility)**                          | **공개 메시지(Interface)**                                                                                                                                                                |
|--------------------------------|-------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Application**                | 프로그램 진입점                                        | `void main(String[])`                                                                                                                                                                |
| **LottoController**            | 게임 전체 흐름 제어 *(입력 → 구매 → 당첨 확인 → 결과 출력)*         | `void run()`                                                                                                                                                                         |
| **InputHandler**               | 입력 처리 오케스트레이션 *(재시도 포함)*                        | `PurchaseAmount inputPurchaseAmount()`<br>`WinningNumbers inputWinningNumbers()`                                                                                                     |
| **RetryHandler**               | 예외 발생 시 재입력 처리                                  | `<T> T retryUntilValid(Supplier<T>)`                                                                                                                                                 |
| **InputView**                  | 사용자 입력 수집                                       | `String readPurchaseAmount()`<br>`List<String> readWinningNumbers()`<br>`String readBonusNumber()`                                                                                   |
| **OutputView**                 | 결과 및 통계 출력                                      | `void printPurchaseCount(int)`<br>`void printLottos(List<Lotto>)`<br>`void printStatistics(WinningStatistics)`<br>`void printProfitRate(double)`<br>`void printErrorMessage(String)` |
| **LottoMachine**               | 로또 구매 및 당첨 통계 계산                                | `void purchase(PurchaseAmount)`<br>`WinningStatistics calculateStatistics(WinningNumbers)`<br>`List<Lotto> getPurchasedLottos()`<br>`int getPurchasedCount()`                        |
| **LottoNumberGenerator**       | 로또 번호 생성 전략 *(인터페이스)*                           | `List<Integer> generate()`                                                                                                                                                           |
| **RandomLottoNumberGenerator** | 랜덤 번호 생성 구현체                                    | `List<Integer> generate()`                                                                                                                                                           |
| **Lotto**                      | 로또 번호 6개 관리 *(일급 컬렉션)*<br>번호 검증 및 포함 여부 확인      | `static Lotto from(List<String>)`<br>`List<Integer> getNumbers()`<br>`boolean contains(int)`                                                                                         |
| **PurchaseAmount**             | 구입 금액 관리 *(원시값 포장)*<br>금액 검증 및 로또 개수 계산         | `static PurchaseAmount from(String)`<br>`int getLottoQuantity()`<br>`int getAmount()`                                                                                                |
| **WinningNumbers**             | 당첨 번호 + 보너스 번호 관리 *(조합)*<br>등수 판정               | `Rank match(Lotto purchasedLotto)`                                                                                                                                                   |
| **BonusNumber**                | 보너스 번호 관리 *(원시값 포장)*<br>범위 검증 및 중복 검증           | `static BonusNumber of(String, Lotto)`<br>`int getValue()`                                                                                                                           |
| **Rank**                       | 등수 정보 관리 *(Enum)*<br>등수 판정 및 상금 조회              | `static Rank valueOf(int matchCount, boolean hasBonus)`<br>`int getPrizeAmount()`<br>`String getDescription()`<br>`boolean isWinning()`                                              |
| **WinningStatistics**          | 당첨 통계 관리 *(일급 컬렉션)*<br>총 당첨 금액 계산<br>**수익률 계산** | `static WinningStatistics from(List<Rank>)`<br>`int getCountByRank(Rank)`<br>`long calculateTotalPrize()`<br>**`ProfitRate calculateProfitRate(PurchaseAmount)`**                    |
| **ProfitRate**                 | 수익률 관리 *(원시값 포장)*<br>수익률 계산 및 반올림               | `static ProfitRate of(long totalPrize, PurchaseAmount)`<br>`double getValue()`                                                                                                       |
| **InputValidator**             | 입력 문자열 형식 검증 *(쉼표 구분자 등)*                       | `static void validateInput(String)`                                                                                                                                                  |
| **InputParser**                | 입력 문자열 파싱 *(쉼표 기준 분리)*                          | `static List<String> parseToStringList(String)`                                                                                                                                      |
| **ErrorMessage**               | 에러 메시지 중앙 관리 *(Enum)*                           | `String getMessage()`                                                                                                                                                                |


