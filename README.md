# 웹 애플리케이션 서버
## 진행 방법
* 웹 애플리케이션 서버 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---

## Step 1 - TDD 실습
### WAS 요구사항
- HTTP 요청과 응답을 파싱해 원하는 값을 가져올 수 있는 API를 제공
- API는 TDD로 구현
- RequestLine을 파싱한다. HTTP 요청의 첫번째 라인을 의미
  - [X] GET RequestLine 파싱
    - "GET /users HTTP/1.1"을 파싱하면 다음과 같은 결과를 얻을 수 있어야 한다.  
      method는 GET, path는 /users, protocol은 HTTP, version은 1.1
      - [X] HttpMethod
        - 문자열을 받아 HttpMethod를 반환한다. 
        - 잘못된 문자열의 경우 Exception이 발생한다.
      - [X] HttpPath
        - 문자열을 받아 HttpPath를 반환한다.
        - "/path" 형식이 아닌경우 Exception이 발생한다. 
      - [X] HttpProtocol
        - 문자열을 받아 HttpProtocol 반환한다.
        - HttpProtocol Protocol과 Version으로 이루어진다.
        - "HTTP/1.1" 형식이 아닌 경우 Exception이 발생한다.
  - [X] POST RequestLine 파싱
    - "POST /users HTTP/1.1"을 파싱
  - [X] Querystring RequestLine 파싱

### 피드백
1. NotNull 대신 값을 확인해보는 테스트
2. parseKeyValue, isValidLength params와 분리
3. getHttpParams에서 객체 자체 리턴 대신 필요한 정보만 리턴

## Step 2 - HTTP 웹 서버 구현
### 기능 요구사항
- http://localhost:8080/index.html 로 접속했을 때 webapp 디렉토리의 index.html 파일을 읽어 클라이언트에 응답한다.
  - 힌트
    - InputStream -> BufferedReader
    - http header 전체 출력 -> !"".equals(line)"
- 요구사항 도출
  - InputStream을 읽어 HttpRequest를 생성한다.
  - HttpRequest
    - -> RequestLine 
    - -> HttpHeaders
      - [ ] 문자열 목록을 받아 HttpHeader List를 생성한다.
      - [ ] 키로 조회하면 일치하는 HttpHeader의 밸류를 반환한다.
      - -> HttpHeader
        - [X] 문자열을 받아 HttpHeader를 생성한다.
          - 키, 밸류로 구성된다.
          - ": "를 기준으로 키, 밸류를 나눈다.
        - [ ] 잘못된 문자열을 받으면 exception이 발생한다.
    - -> HttpBody (나중에)
  - 현재 단계의 요구사항을 구현하기 위해 단순히 FileIoUtils를 사용한다. 
  - (나중에) HttpResponse를 만들어 OutputStream에 쓴다. 
    - -> HttpStatus
    - -> HttpHeaders
    - -> HttpBody
