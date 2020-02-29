/*
 Navicat Premium Data Transfer

 Source Server         : local_pg
 Source Server Type    : PostgreSQL
 Source Server Version : 100012
 Source Host           : localhost:5432
 Source Catalog        : ganxu
 Source Schema         : ganxu

 Target Server Type    : PostgreSQL
 Target Server Version : 100012
 File Encoding         : 65001

 Date: 29/02/2020 19:07:59
*/


-- ----------------------------
-- Sequence structure for ganxu_common_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "ganxu"."ganxu_common_seq" CASCADE;
CREATE SEQUENCE "ganxu"."ganxu_common_seq"
INCREMENT 1
MINVALUE  1000
MAXVALUE 9223372036854775807
START 1000
CACHE 1;

-- ----------------------------
-- Table structure for oauths
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."oauths";
CREATE TABLE "ganxu"."oauths" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "oauth_type" int2 NOT NULL,
  "oauth_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "unionid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "credential" varchar(255) COLLATE "pg_catalog"."default",
  "version" int8 DEFAULT 0,
  "delete_status" int2 DEFAULT 0,
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "ganxu"."oauths"."user_id" IS 'users表主键';
COMMENT ON COLUMN "ganxu"."oauths"."oauth_type" IS '1、微信，2、QQ，3、支付宝，4、其他';
COMMENT ON COLUMN "ganxu"."oauths"."oauth_id" IS '第三方 uid 、openid 等';
COMMENT ON COLUMN "ganxu"."oauths"."unionid" IS 'QQ / 微信同一主体下 Unionid 相同';
COMMENT ON COLUMN "ganxu"."oauths"."credential" IS '密码凭证 /access_token (目前更多是存储在缓存里)';
COMMENT ON COLUMN "ganxu"."oauths"."version" IS '版本（更新锁）';
COMMENT ON COLUMN "ganxu"."oauths"."delete_status" IS '删除状态：0-未删除，其它删除';
COMMENT ON COLUMN "ganxu"."oauths"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."oauths"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."oauths" IS '用户鉴权表';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."users";
CREATE TABLE "ganxu"."users" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar_url" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default",
  "user_type" int2 NOT NULL,
  "version" int8 DEFAULT 0,
  "delete_status" int2 DEFAULT 0,
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "ganxu"."users"."id" IS '用户ID（主键）';
COMMENT ON COLUMN "ganxu"."users"."user_name" IS '用户名';
COMMENT ON COLUMN "ganxu"."users"."avatar_url" IS '头像URL';
COMMENT ON COLUMN "ganxu"."users"."phone" IS '手机';
COMMENT ON COLUMN "ganxu"."users"."password" IS '密码';
COMMENT ON COLUMN "ganxu"."users"."user_type" IS '1、管理员用户，2、普通用户，3、商家用户';
COMMENT ON COLUMN "ganxu"."users"."version" IS '版本（更新锁）';
COMMENT ON COLUMN "ganxu"."users"."delete_status" IS '删除状态：0-未删除，其它删除';
COMMENT ON COLUMN "ganxu"."users"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."users"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."users" IS '用户表';


-- ----------------------------
-- Primary Key structure for table oauths
-- ----------------------------
ALTER TABLE "ganxu"."oauths" ADD CONSTRAINT "oauths_pkey" PRIMARY KEY ("id");


-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "ganxu"."users" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");
-- ----------------------------
-- Indexes structure for table users
-- ----------------------------
CREATE UNIQUE INDEX "index_users_phone" ON "ganxu"."users" USING btree (
  "phone" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
)  WHERE "ganxu"."users"."delete_status" = 0 ;
CREATE UNIQUE INDEX "index_users_user_name" ON "ganxu"."users" USING btree (
  "user_name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
)  WHERE "ganxu"."users"."delete_status" = 0 ;
