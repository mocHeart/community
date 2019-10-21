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