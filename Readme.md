# GitHub 협업 실습 가이드

## 프로젝트 소개

이 프로젝트는 GitHub의 다양한 협업 기능(Issue, Pull Request, Project 등)을 실습하기 위한 간단한 계산기 애플리케이션입니다. 프론트엔드(JavaScript)와 백엔드(Java) 두 가지 버전의 계산기가 포함되어 있으며, 현재는 덧셈 기능만 구현되어 있습니다.

## 실습 목표

- GitHub Issue 생성 및 관리
- Branch 생성 및 관리
- Pull Request 생성, 리뷰, 병합
- 코드 충돌 해결
- Project 보드를 활용한 작업 관리

## 프로젝트 구조

```
github-onboarding/
├── js-calculator/        ← 프론트엔드용
├── java-calculator/      ← 백엔드용
├── .gitignore
└── Readme.md
```

# Java 계산기 실행 방법

이 프로젝트는 GitHub 협업 실습을 위한 Spring Boot 기반의 계산기 API 서비스입니다.

## 프로젝트 소개

단순한 계산기 API를 기반으로 GitHub의 이슈 관리, 브랜치 관리, 풀 리퀘스트, 코드 리뷰 등의 기능을
실습해볼 수 있는 프로젝트입니다. 현재는 덧셈 기능만 구현되어 있으며, 뺄셈, 곱셈, 나눗셈 기능은
GitHub 이슈를 통해 협업하여 구현할 예정입니다.

## 기술 스택

- Java 17
- Spring Boot 3.1.0
- Gradle
- Spring Web
- Spring Validation
- Springdoc OpenAPI
- JUnit 5

## 시작하기

### 요구사항

- JDK 17 이상
- Gradle 7.x 이상

### 빌드 및 실행

1. 프로젝트를 클론합니다.

```bash
git clone https://github.com/Team-SoFa/Team-Onboarding.git
```

2. 프로젝트 디렉토리로 이동합니다.

```bash
cd java-calculator
```

3. Gradle로 프로젝트를 빌드합니다.

```bash
./gradlew build
```

4. 애플리케이션을 실행합니다.

5. 애플리케이션이 실행되면 <http://localhost:8080> 에서 접근할 수 있습니다.
    - API 문서는 <http://localhost:8080/swagger-ui.html> 에서 확인할 수 있습니다.

## API 엔드포인트

### 계산 수행

- **POST** `/api/calculator/calculate`
  - 요청 본문: `{ "num1": 10, "num2": 5, "operation": "add" }`
  - 지원하는 연산: `add`, `subtract`, `multiply`, `divide` (현재는 `add`만 구현됨)

### 덧셈 수행

- **GET** `/api/calculator/add?num1=10&num2=5`

## 테스트

테스트를 실행하려면 다음 명령어를 사용합니다.

```bash
./gradlew test
```

# JavaScript 계산기 실행 방법

이 프로젝트는 GitHub 협업 실습을 위한 계산기 애플리케이션입니다. Next.js와 TypeScript를 사용하여 개발되었으며, GitHub Issue, PR, Project를 활용한 협업 방식을 익히기 위한 목적으로 만들어졌습니다.

## 로컬 개발 환경 설정

```bash
# 저장소 클론
git clone https://github.com/Team-SoFa/Team-Onboarding.git
cd js-calculator

# 패키지 설치
npm install

# 개발 서버 실행
npm run dev
```

## 협업 실습 진행 방법

1. 이슈 생성: 구현할 기능을 이슈로 등록합니다.
2. 브랜치 생성: 각 이슈별로 작업 브랜치를 생성합니다. (예: `feature/subtract`)
3. 코드 작성: 해당 기능을 구현합니다.
4. Pull Request 생성: 작업이 완료되면 PR을 생성하여 코드 리뷰를 요청합니다.
5. 코드 리뷰: 팀원들과 코드를 리뷰하고 피드백을 주고받습니다.
6. 병합: 리뷰가 완료되면 메인 브랜치에 병합합니다.

## 이슈 예시

1. 뺄셈 기능 구현
2. 곱셈 기능 구현
3. 나눗셈 기능 구현
