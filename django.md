# WEB 개발자를 위한 안내서

---

## A. 도움이 될만한 사이트
- 기본적으로 도움이 될만한 사이트는 아래와 같다.
  - Django Docs: https://docs.djangoproject.com/en/3.2/
  - Flask Docs: https://flask.palletsprojects.com/en/2.0.x/
  - Celery Docs: https://docs.celeryproject.org/en/stable/
  - Bootstrap Docs: https://getbootstrap.com/docs/5.0/getting-started/introduction/
  - JS 라이브러리: 보통 자체 Docs를 제공한다. `검색해보길 바란다.`
  - MVC 혹은 MVT 패턴에 대한 이해: `검색해보길 바란다.`

---

## B.1. Front 기본 구조
  - 아래 구조가 front 개발 폴더 대부분의 기본 구조이며, 설명은 다음과 같다.
    ```sh
    /server-conf # 프록시 서버(현재는 nginx) 설정 파일 존재
    /static      # 프록시 서버(현재는 nginx)가 있으면 프록시 서버가 로딩할 정적 파일들
      /css       # HTML에서 불러올 CSS 집합
      /fonts     # HTML에서 사용할 폰트 혹은 아이콘 집합
      /images    # HTML에서 불러올 내부 이미지 집합
      /js        # HTML에서 불러올 내부구현(테마자체 JS 포함) javascript 코드 집합
      /lang      # HTML에서 사용할 언어에 따른 key-value 선언 파일 집합
      /libs      # HTML 내부에서 불러올 JS 라이브러리 집합
    /templates   # 장고에서 기본적으로 사용할 HTML 파일들을 저장
      /partial   # 대부분의 HTML에서 자주 사용되는 파일들을 분리하여 저장 ( C 에서 include 하는 방식과 비슷 )
      ****.html  # 보통 페이지 HTML 파일. 내부에서 템플릿 엔진을 사용함
    ```

  - #### 테마 변경
    > `FrontEnd 는 테마/라이브러리(보통 차트 라이브러리) 등에 의해 유연하게 대체될 수 있으며, 사용할 테마/라이브러리에 맞춰 classname 등을 변경해줘야한다.`

    - `/static` 폴더에 사용하고자 하는 테마의 코드를 옮기면 된다.
    - 기본적으로 신규 테마들은 자체 wiki를 가지고 있어서, 제공된 테마의 Docs를 보면 된다.
    
    - 대부분의 상용 테마는 `/dist`와 같은 이름의 output 폴더를 가지고 있으며, 해당 폴더 내부의 구조를 위의 코드 구조대로 옮겨주면 된다.
    
  - #### 내부 파일 변경
    - 필요한 작업들은 위의 코드 구조에서 필요한 항목을 변경해주면 된다.
      - e.g. css 변경 - `/static/css` 하위의 파일 변경
        - `그러나, 이는 별로 추천하지 않는다. 기존 파일은 그대로 두고, 새로운 CSS 파일을 생성해 overwrite 혹은 override 하는걸 추천한다.`

  - #### UI(Layout 등) 변경
    - `bootstrap` 속성을 보고 기본적으로 변경하되, `테마, 라이브러리 설정`에 따라 추가적으로 변경해주어야한다.

## B.2. Django 기본 구조
  - 아래 구조가 django의 기본 구조이며, 설명은 다음과 같다.
    ```sh
    /{APP_NAME}     # 사용할 APP 명을 적는다. APP은 한개 이상이 될수 있다.
       __init__.py  # APP 이 로드될 때, 수행할 행동을 선언한다.
       apps.py      # APP 의 기본 명을 선언한다.
       settings.py  # APP 의 기본 설정들을 정의한다. (DB, 사용할 패키지 등 다양한 정보들이 있다.)
       wsgi.py      # WSGI에 기본적으로 설정할 정보를 선언한다.
       asgi.py      # ASGI에 기본적으로 설정할 정보를 선언한다.
       urls.py      # WEB Sever에 들어온 요청 URI 패턴을 어느 함수에 매핑시킬지를 선언한다.
       celery.py    # celery 의 task 를 선언한다. 주기, 비동기분산 작업을 실행하고자 할때 사용된다.
       routing.py   # websocket 라우팅 룰을 선언한다.
       models.py    # django ORM으로 사용할 모델을 정의한다.
       forms.py     # django ORM으로 사용할 모델을 생성/수정/삭제 등에 활용할 기본 form을 정의한다.
       views.py     # web 페이지 렌더링 및 페이지에서 수행되는 대부분의 기능들을 담당한다. 페이지 내부에 필요한 정보들을 포함시켜 렌더링 할 수 있다.
       tests.py     # 테스트 코드를 작성한다. (거의 사용하지 않는다. 후임자는 이 파일을 자주 사용했으면 좋겠다. TDD는 중요하다.)
    /staticfiles    # 설정에 따라 다르겠지만, 대부분 사내 프로젝트는 Django에서 기본으로 사용하는 테마 파일들을 넣어둔다. (/admin 으로 들어갔을때 사용되는 파일들)
    /locale         # Django에서 생성해주는 언어 폴더이다.
    manage.py       # Django가 제공하는 기능들(계정 생성, model 생성, 번역 문구 추출 등)을 사용할 수 있도록 하며, `python3 manage.py <command>`를 통해 사용한다.
    ```

  - #### 사용자 추가하기
    - `python3 manage.py createsuperuser`
      - 결과물: `django backend DB 의 auth_user 테이블`

  - #### 번역하기
    - `python3 manage.py makemessages`
      - 결과물: `/locale/<LANG_CODE>/django.po`
      - python 내의 `_("MESSAGE")`, HTML 내의 `{{ _("MESSAGE") }}` 로 선언된 모든 메세지를 번역에 사용할 문구로 취합해준다.
    - `python3 manage.py compilemessages`
      - 결과물: `/locale/<LANG_CODE>/django.mo`
      - 위에서 `makemessages`를 통해 생성된 `django.po`파일에 번역을 기재한 뒤, `compilemessages` 명령을 수행하면, `django.mo` 파일이 생성되며 이를 웹서버에 적용시키기 위해서는 보통 django 서비스를 재실행시켜줘야한다.

  - #### Migrate (model 변경하기)
    - `python3 manage.py makemigrations`
      - 결과물: `/<APP>/migrations/NNNN_<name>_YYYYMMDD_*.py` 생성, `django backend DB 의 django_migrations 테이블`
      - `models.py`의 변경사항을 Django에 알린다.
    - `python3 manage.py migrate`
      - 결과물: `django backend DB 의 각 model 테이블 생성/삭제/수정`
      - `models.py`의 변경사항을 실제 Backend DB에 적용한다. 이를 웹서버에 적용시키기 위해서는 보통 django 서비스를 재실행시켜줘야한다.

---
