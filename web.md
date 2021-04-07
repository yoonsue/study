### URI / URL / URN
- 결론: W3C가 URL과 URI를 구분하지 않을것이고, 내부 타입 구분을 엄격히 할 필요가 없다고 함 - 참고 URL: (https://www.w3.org/TR/uri-clarification/)
- `http://www.google.co.kr:80/search?q=question&newwindow=1#AAA` - 참고 URL: (https://programming119.tistory.com/194)
  - Method(Protocol): `http://`
  - Subdomain: `www`
  - Domain: `google`
  - ccTLD(Country Code Top Level Domain): `co`
  - TLD(Top Level Domain): `kr`
  - Port: `:80`
  - Document Path: `/search`
  - Query Parameter: `?q=question&newwindow=1`
  - URI fragment(Anchor): `#AAAA`

- URI ⊃ URL, URI ⊃ URN - 참고 URL: (https://stackoverflow.com/questions/4913343/what-is-the-difference-between-uri-url-and-urn)

| 식별자 | 풀네임 | 차이 | 구분점 | 예 |
| --- | --------------------------- | --- | ------ | -- |
| URI | Uniform Resource Identifier | 가장 상위 개념 | 프로토콜부터 파라미터까지 | `data:,Hello%20World`, `http://www.google.co.kr:80/search?q=question&newwindow=1#AAA` |
| URC | Uniform Resource Citation | Document의 메타데이터를 가르키는 URI, URL도 URN도 아님. | - | `view-source:http://example.com/` |
| URL | Uniform Resource Locator | How&Where, 어떤 매커니즘으로 어디서(절대경로,상대경로 무관) 리소스를 얻을 수 있는지 | 프로토콜(ftp, smb, ...)부터 자원 path까지 (네트워크상 자원표현도 가능, 일반적으로 사이트 도메인) | `http://www.google.co.kr:80/search?q=question&newwindow=1#AAA` |
| URN | Uniform Resource Name | What(Not How), 아이디어와 컨셉, 식별가능한 리소스를 나타내진 않음 | 서브도메인부터 파라미터까지 | `www.google.co.kr:80/search?q=question&newwindow=1#AAA` |
  - URN
    `<URN> ::= "urn:" <NID> ":" <NSS>`
    - NID: Namespace Identifier
    - NSS: Namespace Specific String

  - 구분 예제
    - URL: ftp://ftp.is.co.za/rfc/rfc1808.txt
    - URL: http://www.ietf.org/rfc/rfc2396.txt
    - URL: ldap://[2001:db8::7]/c=GB?objectClass?one
    - URL: mailto:John.Doe@example.com
    - URL: news:comp.infosystems.www.servers.unix
    - URL: telnet://192.0.2.16:80/
    - URN (not URL): urn:oasis:names:specification:docbook:dtd:xml:4.1.2
    - URN (not URL): urn:isbn:0-486-27557-4
 
 - 알쓸신잡..
   - HTML A tag는 URL이 아닌 URI를 넣어야 함 (URN은 가능)
