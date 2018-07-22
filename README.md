
# Spring Security - OAuth JWT


Spring JWT의 인증 플로우:

1. 서버에 요청이 오면 Filter(eg. UsernamePasswordAuthenticationFilter) 등의 필드들을 체인으로 묶어서 요청을 걸러낸다.
2. 인증 요청 객체(extends Authentication)가 만들어진다.
3. 인증 요청 객체는 ProviderManager에게 전달되어서 해당 authentication.class에 맞는 provider를 검색해서 .authenticate()를 하게되어있다.
4. ProviderManager는 인증된 객체(extends Authentication)를 반환해서 인증된 객체를 관리하는 '인증 콘텍스트'에 담기게되고, '인증 콘텍스트 관리자'가 인증 콘텍스트를 관리한다.
5. 인증 콘텍스트 관리자는 콘텍스트 객체를 들고 있다가 필요한 순간에 객체를 뿌려준다 - 주입.

개발자가 구현하는 것들:

- 요청을 받아낼 필터 (AbstractAuthenticatinFilter).
- ProviderManager에 등록시킬 Auth Provider.
- AJAX일 경우, 인증 정보를 담는 DTO.
- 인증 성공, 실패에 따른 핸들러.
- 인증 서버 (인증 공급자)에 맞는 규격의 DTO와 HTTP 객체 (RestTemplate).
- 인증 시도/성공에 사용할 Authentication 객체.


Implicit Grant Flow

![Implicit Grant Flow](/assets/img/implicit-grant-flow.png)

위 그림은 Spring security JWT 사용을 뺀 플로우이다. JWT를 사용하면 인증서버에서 토큰의 유효성을 확인 받은 후 JWT 토큰을 새로 발급해서 응답에 같이 보낸다. 클라이언트는 다음에 보낼 요청들에 JWT를 포함해서 보낸다. 이러면 어플리케이션 서버는 인증 서버를 다시 거칠 필요 없이 바로 요청을 처리할 수 있다. 또, 어플리케이션 서버가 DB에 조회 없이도 인증이 가능하게 한다.

