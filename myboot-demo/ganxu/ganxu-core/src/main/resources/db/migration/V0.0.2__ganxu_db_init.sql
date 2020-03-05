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

 Date: 05/03/2020 14:18:40
*/


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."role";
CREATE TABLE "ganxu"."role" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "role" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."role"."id" IS '角色表主键';
COMMENT ON COLUMN "ganxu"."role"."role" IS '角色标识';
COMMENT ON COLUMN "ganxu"."role"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."role"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."role" IS '角色表';

-- ----------------------------
-- Uniques structure for table role
-- ----------------------------
ALTER TABLE "ganxu"."role" ADD CONSTRAINT "c_role" UNIQUE ("role");
COMMENT ON CONSTRAINT "c_role" ON "ganxu"."role" IS '角色标识唯一';

-- ----------------------------
-- Primary Key structure for table role
-- ----------------------------
ALTER TABLE "ganxu"."role" ADD CONSTRAINT "role_pkey" PRIMARY KEY ("id");


-- ----------------------------
-- Table structure for role_authority
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."role_authority";
CREATE TABLE "ganxu"."role_authority" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "role_id" int8 NOT NULL,
  "perm" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."role_authority"."id" IS '角色权限表主键';
COMMENT ON COLUMN "ganxu"."role_authority"."role_id" IS '角色ID';
COMMENT ON COLUMN "ganxu"."role_authority"."perm" IS '权限标识';
COMMENT ON COLUMN "ganxu"."role_authority"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."role_authority"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."role_authority" IS '角色权限表';

-- ----------------------------
-- Uniques structure for table role_authority
-- ----------------------------
ALTER TABLE "ganxu"."role_authority" ADD CONSTRAINT "c_role_id_perm" UNIQUE ("role_id", "perm");
COMMENT ON CONSTRAINT "c_role_id_perm" ON "ganxu"."role_authority" IS '角色权限唯一键';

-- ----------------------------
-- Primary Key structure for table role_authority
-- ----------------------------
ALTER TABLE "ganxu"."role_authority" ADD CONSTRAINT "role_authority_pkey" PRIMARY KEY ("id");



-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_role";
CREATE TABLE "ganxu"."user_role" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "role_id" int8,
  "role" varchar(60) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user_role"."id" IS '用户信息表主键';
COMMENT ON COLUMN "ganxu"."user_role"."user_id" IS '用户表主键';
COMMENT ON COLUMN "ganxu"."user_role"."role_id" IS '角色表主键ID';
COMMENT ON COLUMN "ganxu"."user_role"."role" IS '角色标识';
COMMENT ON COLUMN "ganxu"."user_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."user_role"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."user_role" IS '用户权限表';

-- ----------------------------
-- Uniques structure for table user_role
-- ----------------------------
ALTER TABLE "ganxu"."user_role" ADD CONSTRAINT "c_user_id_role_id" UNIQUE ("user_id", "role_id");
COMMENT ON CONSTRAINT "c_user_id_role_id" ON "ganxu"."user_role" IS '用户角色唯一键';

-- ----------------------------
-- Primary Key structure for table user_role
-- ----------------------------
ALTER TABLE "ganxu"."user_role" ADD CONSTRAINT "user_role_pkey" PRIMARY KEY ("id");
