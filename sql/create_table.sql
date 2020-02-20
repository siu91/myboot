/*
 Navicat Premium Data Transfer

 Source Server         : local_pg
 Source Server Type    : PostgreSQL
 Source Server Version : 100012
 Source Host           : localhost:5432
 Source Catalog        : primary
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100012
 File Encoding         : 65001

 Date: 20/02/2020 11:24:42
*/

-- ----------------------------
-- 创建序列
-- ----------------------------
CREATE SEQUENCE "public"."public_seq";

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
  "user_id" int8 NOT NULL DEFAULT nextval('public_seq'::regclass),
  "user_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar_url" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."user_info"."user_id" IS '用户ID（主键）';
COMMENT ON COLUMN "public"."user_info"."user_name" IS '用户名';
COMMENT ON COLUMN "public"."user_info"."avatar_url" IS '头像URL';
COMMENT ON COLUMN "public"."user_info"."phone" IS '手机';
COMMENT ON COLUMN "public"."user_info"."password" IS '密码';
COMMENT ON COLUMN "public"."user_info"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."user_info"."update_time" IS '更新时间';

-- ----------------------------
-- Uniques structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD CONSTRAINT "u_user_name" UNIQUE ("user_name");
ALTER TABLE "public"."user_info" ADD CONSTRAINT "u_phone" UNIQUE ("phone");
COMMENT ON CONSTRAINT "u_user_name" ON "public"."user_info" IS '用户名唯一';
COMMENT ON CONSTRAINT "u_phone" ON "public"."user_info" IS '手机唯一';


-- ----------------------------
-- Table structure for oauths
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauths";
CREATE TABLE "public"."oauths" (
  "id" int8 NOT NULL DEFAULT nextval('public_seq'::regclass),
  "user_id" int8 NOT NULL,
  "oauth_type" int2 NOT NULL,
  "oauth_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "unionid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "credential" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "public"."oauths"."user_id" IS 'user_info表主键';
COMMENT ON COLUMN "public"."oauths"."oauth_type" IS '1、微信，2、QQ，3、支付宝，4、其他';
COMMENT ON COLUMN "public"."oauths"."oauth_id" IS '第三方 uid 、openid 等';
COMMENT ON COLUMN "public"."oauths"."unionid" IS 'QQ / 微信同一主体下 Unionid 相同';
COMMENT ON COLUMN "public"."oauths"."credential" IS '密码凭证 /access_token (目前更多是存储在缓存里)';

-- ----------------------------
-- Primary Key structure for table oauths
-- ----------------------------
ALTER TABLE "public"."oauths" ADD CONSTRAINT "oauths_pkey" PRIMARY KEY ("id");
