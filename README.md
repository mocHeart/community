## moc社区

## 资料
1. [Spring 文档](https://spring.io/guides)
2. [Spring Web](https://spring.io/guides/gs/serving-web-content)
3. [elastic 中文社区](https://elasticsearch.cn/explore)
4. [BootStrap文档](https://v3.bootcss.com/getting-started)
5. [码匠源码](https://github.com/codedrinker/community)
6. [GitHub API](https://developer.github.com)
7. [GitHub OAuth1](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app)
8. [GitHub OAuth2](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps)
9. [OKHttp](https://square.github.io/okhttp)
10. [H2 Quickstart](http://www.h2database.com/html/quickstart.html)
11. [Spring Boot 官方文档](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/)

## 工具
1. [git 下载](https://git-scm.com/downloads)
2. [Visual Paradigm](https://www.visual-paradigm.com/)

## 脚本
1. User表DDL
```sql
create table USER
(
	ID INT default auto_increment,
	ACCOUNT_ID VARCHAR(128),
	NAME VARCHAR(64),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
	primary key (ID)
);
```

