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
11. [Spring Boot 官方文档](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config-interceptors)
12. [Thymeleaf doc](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#introducing-thymeleaf)
13. [Moment.js 文档](http://momentjs.cn/docs/)
14. [MarkDown 插件](https://pandao.github.io/editor.md/)

## 工具
1. [git 下载](https://git-scm.com/downloads)
2. [Visual Paradigm](https://www.visual-paradigm.com)
3. [flywaydb](https://flywaydb.org/getstarted/firststeps/maven#creating-the-first-migration)
4. [Lombok](https://projectlombok.org/features/all)
5. [Postman谷歌插件](https://chrome.google.com/webstore/detail/tabbed-postman-rest-clien/coohjcphdfgbiolnekdpbcijmhambjff)

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
2. User表增加bio字段
```sql
alter table USER add bio varchar(256);
```
3. Question表DDL
```sql
create table QUESTION
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(50),
	description TEXT,
	gmt_create BIGINT,
	gmt_modified BIGINT,
	creator INT,
	comment_count INT DEFAULT 0,
	view_count INT DEFAULT 0,
	like_count INT DEFAULT 0,
	tag VARCHAR(256)
);
```
4. User表增加头像地址avatar_url字段
```sql
alter table USER
add avatar_url varchar(128) null;
```

5. COMMENT表DDL
```sql
CREATE TABLE COMMENT
(
	id BIGINT AUTO_INCREMENT PRIMARY KEY ,
	parent_id BIGINT NOT NULL,
	type INT NOT NULL, -- 父类类型
	commentator INT NOT NULL,
	gmt_create BIGINT NOT NULL,
	gmt_modified BIGINT NOT NULL,
	like_count BIGINT DEFAULT 0,  -- 点赞数
);

-- 增加字段
ALTER TABLE COMMENT
	ADD content VARCHAR(1024) NULL;
```

6. 改变表的ID字段等的类型
```sql
ALTER TABLE QUESTION ALTER COLUMN ID BIGINT DEFAULT NOT NULL ;
ALTER TABLE QUESTION ALTER COLUMN CREATOR BIGINT DEFAULT NOT NULL ;
ALTER TABLE USER ALTER COLUMN ID BIGINT DEFAULT NOT NULL ;
ALTER TABLE COMMENT ALTER COLUMN COMMENTATOR BIGINT DEFAULT NOT NULL ;
```

5. bash
```shell
// 更新数据库
mvn flyway:migrate
// mybatis-generator 自动生成
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```

## 注意
1. 数据库中存在 text 等字段映射到 Java String 上时，在 Mybatis Generator 中要使用带 WithBLOBs 的方法进行查询。