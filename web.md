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

---
### CSS
- display 와 visibility 속성: https://webdir.tistory.com/348

---
### Bootstrap
- http://bootstrapk.com/css/

#### 그리드 시스템
- 웹 페이지를 행과 12개의 열로 나누어서 레이아웃 할수 있도록
- 화면 사이즈(모바일 등)에 따라 반응형 웹이 되도록

##### 1. 구성
( container(fixed-width) | container-fluid(full-width) ) ⊃ 
- col 동작 방식: 
  - padding을 통해 간격을 만듬
  - 마진 값이 음수로 들어 있어 offset 으로 사용됨
    - 비그리드 콘텐츠와 정렬되기 위함

##### 2. 미디어 쿼리 (`@media (min-width: *** )`)
- 작은 기기의 그리드 클래스가 오버라이드됨 (`col-md-*` 적용중인 UI에 `col-lg-*` 가 없을 경우에도 적용)
###### 2.1. 주요 분기점
```css
/* 작은 기기(모바일폰, 768px >=) */
@media (min-width: @screen-sm-min) { ... }
/* 중간 기기(태블릿, 992px >=) */
@media (min-width: @screen-md-min) { ... }
/* 큰 기기(데스크탑, 1200px >=) */
@media (min-width: @screen-lg-min) { ... }
```
- `@screen-sm-max` 도 존재함

###### 2.2. 미디어 쿼리
| 클래스 | CSS 스타일 |
| -- | -- | 
| `.hidden-*` | `display: block;` |
| `.visible-*-block` | `display: block;` |
| `.visible-*-inline` | `display: inline;` |
| `.visible-*-inline-block` | `display: inline-block;` |
| `.visible-print-inline-block` | `display: inline-block;` |

##### 3. 그리드 옵션
|	| 매우 작은 기기 모바일폰 (<768px) | 작은 기기 태블릿 (≥768px) | 중간 기기 데스크탑 (≥992px) | 큰 기기 데스크탑 (≥1200px) |
| -- | -- | -- | -- | -- |
| 그리드 적용 |	항상 |	분기점보다 크면 적용 | 좌동 | 좌동 |
| 콘테이너 너비 |	없음 (auto) |	750px |	970px |	1170px |
| 클래스 접두사 |	.col-xs- |	.col-sm- |	.col-md- |	.col-lg- |
| 컬럼 수 |	12 | 좌동 | 좌동 | 좌동 |
| 컬럼 너비 |	Auto |	~62px |	~81px |	~97px |
| 사이 너비	| 30px (컬럼의 양쪽에 15px 씩) | 좌동 | 좌동 | 좌동 |
| 중첩 | 예 | 좌동 | 좌동 | 좌동 |
| 오프셋 |	예 | 좌동 | 좌동 | 좌동 |
| 컬럼 순서정하기 |	예 | 좌동 | 좌동 | 좌동 |

##### 유용할 클래스 & 변수
```css
/* TODO: ??? */
.clearfix

/* 열을 우측으로 옮긺 */
.col-*-offset-*

/* 열 순서 정하기 */
.col-*-push-*
.col-*-pull-*

/* LESS 믹스인과 변수 */
/* 변수 */
@grid-columns: 12;
@grid-gutter-width: 30px;
@grid-float-breakpoint: 768px;
/* 믹스인: 그리드 변수와 함께 그리드 컬럼을 위한 시맨틱 CSS를 생성하는데 사용 */
     - TODO: 시맨틱 CSS: ??? 
     - TODO: @gutter: ??? */
// 일련의 컬럼을 위해 wrapper 를 생성합니다.
.make-row(@columns; @gutter: @grid-gutter-width) {
  // Then clear the floated columns
  .clearfix();

  @media (min-width: @screen-sm-min) {
    margin-left:  (@gutter / -2);
    margin-right: (@gutter / -2);
  }

  // 컬럼들의 콘텐츠를 정렬하기 위해 중첩된 행들에 음수 마진을 줍니다
  .row {
    margin-left:  (@gutter / -2);
    margin-right: (@gutter / -2);
  }
}

```

##### 테이블 열 너비 조정하기 (https://stackoverflow.com/questions/15115052/how-to-set-up-fixed-width-for-td?answertab=votes#tab-top)
###### For Bootstrap 4.0
```html
<tr>
  <th style="width: 16.66%">Col 1</th>
  <th style="width: 25%">Col 2</th>
  <th style="width: 50%">Col 4</th>
  <th style="width:  8.33%">Col 5</th>
</tr>
```
