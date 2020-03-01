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

 Date: 01/03/2020 08:42:24
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
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."order";
CREATE TABLE "ganxu"."order" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "order_sn" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "user_id" int8 NOT NULL,
  "shipping_user" varchar(255) COLLATE "pg_catalog"."default",
  "province" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "city" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "district" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(512) COLLATE "pg_catalog"."default" NOT NULL,
  "payment_method" int2,
  "order_amount" int8,
  "discount_amount" int8,
  "shipping_amount" int8,
  "payment_amount" int8,
  "shipping_comp_name" varchar(255) COLLATE "pg_catalog"."default",
  "shipping_sn" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "shipping_time" timestamp(0),
  "pay_time" timestamp(0),
  "receive_time" timestamp(0),
  "order_status" int2,
  "order_point" int8,
  "invoice_id" int8,
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."order"."id" IS '订单ID';
COMMENT ON COLUMN "ganxu"."order"."order_sn" IS '订单编号';
COMMENT ON COLUMN "ganxu"."order"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."order"."shipping_user" IS '收货人';
COMMENT ON COLUMN "ganxu"."order"."province" IS '省';
COMMENT ON COLUMN "ganxu"."order"."city" IS '市';
COMMENT ON COLUMN "ganxu"."order"."district" IS '区';
COMMENT ON COLUMN "ganxu"."order"."address" IS '地址';
COMMENT ON COLUMN "ganxu"."order"."payment_method" IS '支付方式：1现金，2余额，3网银，4支付宝，5微信';
COMMENT ON COLUMN "ganxu"."order"."order_amount" IS '订单金额';
COMMENT ON COLUMN "ganxu"."order"."discount_amount" IS '优惠金额';
COMMENT ON COLUMN "ganxu"."order"."shipping_amount" IS '运费金额';
COMMENT ON COLUMN "ganxu"."order"."payment_amount" IS '支付金额';
COMMENT ON COLUMN "ganxu"."order"."shipping_comp_name" IS '快递公司名称';
COMMENT ON COLUMN "ganxu"."order"."shipping_sn" IS '快递单号';
COMMENT ON COLUMN "ganxu"."order"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."order"."shipping_time" IS '发货时间';
COMMENT ON COLUMN "ganxu"."order"."pay_time" IS '支付时间';
COMMENT ON COLUMN "ganxu"."order"."receive_time" IS '收货时间';
COMMENT ON COLUMN "ganxu"."order"."order_status" IS '订单状态';
COMMENT ON COLUMN "ganxu"."order"."order_point" IS '订单积分';
COMMENT ON COLUMN "ganxu"."order"."invoice_id" IS '发票ID';
COMMENT ON COLUMN "ganxu"."order"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."order" IS '订单主表';

-- ----------------------------
-- Table structure for order_cart
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."order_cart";
CREATE TABLE "ganxu"."order_cart" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "product_id" int8 NOT NULL,
  "product_amount" int2 NOT NULL,
  "price" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."order_cart"."id" IS '购物车ID';
COMMENT ON COLUMN "ganxu"."order_cart"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."order_cart"."product_id" IS '商品ID';
COMMENT ON COLUMN "ganxu"."order_cart"."product_amount" IS '加入购物车商品数量';
COMMENT ON COLUMN "ganxu"."order_cart"."price" IS '商品价格';
COMMENT ON COLUMN "ganxu"."order_cart"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."order_cart"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."order_cart" IS '购物车';

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."order_detail";
CREATE TABLE "ganxu"."order_detail" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "order_id" int8 NOT NULL,
  "product_id" int8 NOT NULL,
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "product_cnt" int2 NOT NULL,
  "product_price" int8 NOT NULL,
  "average_cost" int8,
  "weight" float8,
  "discount_amount" int8,
  "w_id" int8,
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."order_detail"."id" IS '订单详情表ID';
COMMENT ON COLUMN "ganxu"."order_detail"."order_id" IS '订单主表ID';
COMMENT ON COLUMN "ganxu"."order_detail"."product_id" IS '商品ID';
COMMENT ON COLUMN "ganxu"."order_detail"."product_name" IS '商品名称';
COMMENT ON COLUMN "ganxu"."order_detail"."product_cnt" IS '购买商品数量';
COMMENT ON COLUMN "ganxu"."order_detail"."product_price" IS '购买商品单价';
COMMENT ON COLUMN "ganxu"."order_detail"."average_cost" IS '平均成本价格';
COMMENT ON COLUMN "ganxu"."order_detail"."weight" IS '商品重量（克）';
COMMENT ON COLUMN "ganxu"."order_detail"."discount_amount" IS '优惠金额（分摊）';
COMMENT ON COLUMN "ganxu"."order_detail"."w_id" IS '仓库ID';
COMMENT ON COLUMN "ganxu"."order_detail"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."order_detail" IS '订单详情表';

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product";
CREATE TABLE "ganxu"."product" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "product_code" int8,
  "product_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "bar_code" varchar(255) COLLATE "pg_catalog"."default",
  "brand_id" int8,
  "category1_id" int8,
  "category2_id" int8,
  "category3_id" int8,
  "supplier_id" int8,
  "price" int8 NOT NULL,
  "origin_price" int8 NOT NULL,
  "average_cost" int8 DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "publish_status" int2 NOT NULL DEFAULT 0,
  "audit_status" int2 NOT NULL DEFAULT 0,
  "weight" float8,
  "length" float8,
  "height" float8,
  "width" float8,
  "unit" int2,
  "color" int2,
  "production_date" date NOT NULL,
  "expiration_date" date NOT NULL,
  "desc" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product"."id" IS '商品ＩＤ';
COMMENT ON COLUMN "ganxu"."product"."product_code" IS '商品编码';
COMMENT ON COLUMN "ganxu"."product"."product_name" IS '商品名称';
COMMENT ON COLUMN "ganxu"."product"."bar_code" IS '国条码';
COMMENT ON COLUMN "ganxu"."product"."brand_id" IS '品牌表的ID';
COMMENT ON COLUMN "ganxu"."product"."category1_id" IS '一级分类ID';
COMMENT ON COLUMN "ganxu"."product"."category2_id" IS '二级分类ID';
COMMENT ON COLUMN "ganxu"."product"."category3_id" IS '三级分类ID';
COMMENT ON COLUMN "ganxu"."product"."supplier_id" IS '商品的供应商ID';
COMMENT ON COLUMN "ganxu"."product"."price" IS '商品销售价格';
COMMENT ON COLUMN "ganxu"."product"."origin_price" IS '原价';
COMMENT ON COLUMN "ganxu"."product"."average_cost" IS '商品加权平均成本';
COMMENT ON COLUMN "ganxu"."product"."publish_status" IS '上下架状态：0下架1上架';
COMMENT ON COLUMN "ganxu"."product"."audit_status" IS '审核状态：0未审核，1已审核';
COMMENT ON COLUMN "ganxu"."product"."weight" IS '商品重量（克）';
COMMENT ON COLUMN "ganxu"."product"."length" IS '商品长度（毫米）';
COMMENT ON COLUMN "ganxu"."product"."height" IS '商品高度（毫米）';
COMMENT ON COLUMN "ganxu"."product"."width" IS '商品宽度（毫米）';
COMMENT ON COLUMN "ganxu"."product"."unit" IS '商品单位：盒/包/台/袋';
COMMENT ON COLUMN "ganxu"."product"."color" IS '颜色';
COMMENT ON COLUMN "ganxu"."product"."production_date" IS '生产日期';
COMMENT ON COLUMN "ganxu"."product"."expiration_date" IS '有效期';
COMMENT ON COLUMN "ganxu"."product"."desc" IS '商品描述';
COMMENT ON COLUMN "ganxu"."product"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product" IS '商品信息表';

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_brand";
CREATE TABLE "ganxu"."product_brand" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "brand_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "telephone" varchar(50) COLLATE "pg_catalog"."default",
  "brand_website" varchar(255) COLLATE "pg_catalog"."default",
  "brand_logo" varchar(255) COLLATE "pg_catalog"."default",
  "brand_desc" varchar(255) COLLATE "pg_catalog"."default",
  "brand_status" int2 DEFAULT 0,
  "brand_order" int4 DEFAULT 0,
  "create_time" timestamp(6),
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_brand"."id" IS '商品品牌信息表ID';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_name" IS '品牌名称';
COMMENT ON COLUMN "ganxu"."product_brand"."telephone" IS '联系电话';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_website" IS '品牌网站';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_logo" IS '品牌logo';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_desc" IS '品牌描述';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_status" IS '品牌状态,0禁用,1启用';
COMMENT ON COLUMN "ganxu"."product_brand"."brand_order" IS '排序';
COMMENT ON COLUMN "ganxu"."product_brand"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_brand"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product_brand" IS '商品品牌信息表';

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_category";
CREATE TABLE "ganxu"."product_category" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "category_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "category_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_id" int4,
  "category_level" int2 NOT NULL DEFAULT 0,
  "category_status" int2 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_category"."id" IS '商品分类ID';
COMMENT ON COLUMN "ganxu"."product_category"."category_name" IS '分类名称';
COMMENT ON COLUMN "ganxu"."product_category"."category_code" IS '分类编码';
COMMENT ON COLUMN "ganxu"."product_category"."parent_id" IS '父分类ID';
COMMENT ON COLUMN "ganxu"."product_category"."category_level" IS '分类层级';
COMMENT ON COLUMN "ganxu"."product_category"."category_status" IS '分类状态';
COMMENT ON COLUMN "ganxu"."product_category"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_category"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product_category" IS '商品分类信息表';

-- ----------------------------
-- Table structure for product_comment
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_comment";
CREATE TABLE "ganxu"."product_comment" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "product_id" int8 NOT NULL,
  "order_id" int8 NOT NULL,
  "user_id" int4 NOT NULL,
  "title" varchar(55) COLLATE "pg_catalog"."default",
  "content" varchar(300) COLLATE "pg_catalog"."default" NOT NULL,
  "audit_status" int2 NOT NULL DEFAULT 0,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_comment"."id" IS '商品评论表ID';
COMMENT ON COLUMN "ganxu"."product_comment"."product_id" IS '商品ID';
COMMENT ON COLUMN "ganxu"."product_comment"."order_id" IS '订单ID';
COMMENT ON COLUMN "ganxu"."product_comment"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."product_comment"."title" IS '评论标题';
COMMENT ON COLUMN "ganxu"."product_comment"."content" IS '评论内容';
COMMENT ON COLUMN "ganxu"."product_comment"."audit_status" IS '审核状态：0未审核，1已审核';
COMMENT ON COLUMN "ganxu"."product_comment"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_comment"."upate_time" IS '更新时间';

-- ----------------------------
-- Table structure for product_pic
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_pic";
CREATE TABLE "ganxu"."product_pic" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "product_id" int8 NOT NULL,
  "pic_desc" varchar(255) COLLATE "pg_catalog"."default",
  "pic_url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "main_pic" int2 NOT NULL DEFAULT 0,
  "pic_order" int2 NOT NULL DEFAULT 0,
  "pic_status" int2 NOT NULL DEFAULT 1,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_pic"."id" IS '商品图片表ID';
COMMENT ON COLUMN "ganxu"."product_pic"."product_id" IS '商品ID';
COMMENT ON COLUMN "ganxu"."product_pic"."pic_desc" IS '图片描述';
COMMENT ON COLUMN "ganxu"."product_pic"."pic_url" IS '图片URL';
COMMENT ON COLUMN "ganxu"."product_pic"."main_pic" IS '是否主图';
COMMENT ON COLUMN "ganxu"."product_pic"."pic_order" IS '排序';
COMMENT ON COLUMN "ganxu"."product_pic"."pic_status" IS '图片是否有效：0无效 1有效';
COMMENT ON COLUMN "ganxu"."product_pic"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_pic"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product_pic" IS '商品图片表';

-- ----------------------------
-- Table structure for product_stock
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_stock";
CREATE TABLE "ganxu"."product_stock" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "product_id" int8 NOT NULL,
  "w_id" int8,
  "current_cnt" int4 NOT NULL DEFAULT 0,
  "lock_cnt" int4 NOT NULL DEFAULT 0,
  "in_transit_cnt" int4 NOT NULL DEFAULT 0,
  "average_cost" int8 DEFAULT 0,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_stock"."id" IS '商品库存表ID';
COMMENT ON COLUMN "ganxu"."product_stock"."product_id" IS '商品ID';
COMMENT ON COLUMN "ganxu"."product_stock"."w_id" IS '仓库ID';
COMMENT ON COLUMN "ganxu"."product_stock"."current_cnt" IS '当前商品数量';
COMMENT ON COLUMN "ganxu"."product_stock"."lock_cnt" IS '当前占用数据';
COMMENT ON COLUMN "ganxu"."product_stock"."in_transit_cnt" IS '在途数据';
COMMENT ON COLUMN "ganxu"."product_stock"."average_cost" IS '移动加权成本';
COMMENT ON COLUMN "ganxu"."product_stock"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_stock"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product_stock" IS '商品库存表';

-- ----------------------------
-- Table structure for product_supplier
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."product_supplier";
CREATE TABLE "ganxu"."product_supplier" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "supplier_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "supplier_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "supplier_type" int2,
  "contact_person" varchar(255) COLLATE "pg_catalog"."default",
  "telephone" varchar(50) COLLATE "pg_catalog"."default",
  "bank_name" varchar(255) COLLATE "pg_catalog"."default",
  "bank_account" varchar(255) COLLATE "pg_catalog"."default",
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "supplier_status" int2 DEFAULT 0,
  "create_time" timestamp(6),
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."product_supplier"."id" IS '商品供应商ID';
COMMENT ON COLUMN "ganxu"."product_supplier"."supplier_name" IS '供应商名称';
COMMENT ON COLUMN "ganxu"."product_supplier"."supplier_code" IS '供应商编码';
COMMENT ON COLUMN "ganxu"."product_supplier"."supplier_type" IS '供应商类型：1.自营，2.平台';
COMMENT ON COLUMN "ganxu"."product_supplier"."contact_person" IS '供应商联系人';
COMMENT ON COLUMN "ganxu"."product_supplier"."telephone" IS '联系电话';
COMMENT ON COLUMN "ganxu"."product_supplier"."bank_name" IS '供应商开户银行名称';
COMMENT ON COLUMN "ganxu"."product_supplier"."bank_account" IS '银行账号';
COMMENT ON COLUMN "ganxu"."product_supplier"."address" IS '供应商地址';
COMMENT ON COLUMN "ganxu"."product_supplier"."supplier_status" IS '状态：0禁止，1启用';
COMMENT ON COLUMN "ganxu"."product_supplier"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."product_supplier"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."product_supplier" IS '商品供应商信息表';

-- ----------------------------
-- Table structure for shipping_info
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."shipping_info";
CREATE TABLE "ganxu"."shipping_info" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "shipping_sn" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "ship_name" varchar(255) COLLATE "pg_catalog"."default",
  "ship_contact" varchar(255) COLLATE "pg_catalog"."default",
  "telephone" varchar(255) COLLATE "pg_catalog"."default",
  "price" int8,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."shipping_info"."shipping_sn" IS '快递单号';
COMMENT ON COLUMN "ganxu"."shipping_info"."ship_name" IS '物流公司名称';
COMMENT ON COLUMN "ganxu"."shipping_info"."ship_contact" IS '物流公司联系人';
COMMENT ON COLUMN "ganxu"."shipping_info"."telephone" IS '物流公司联系电话';
COMMENT ON COLUMN "ganxu"."shipping_info"."price" IS '配送价格';
COMMENT ON COLUMN "ganxu"."shipping_info"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."shipping_info"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."shipping_info" IS '物流公司信息表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user";
CREATE TABLE "ganxu"."user" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar_url" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(64) COLLATE "pg_catalog"."default",
  "version" int8 DEFAULT 0,
  "delete_status" int2 DEFAULT 0,
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user"."id" IS '用户ID（主键）';
COMMENT ON COLUMN "ganxu"."user"."user_name" IS '用户名';
COMMENT ON COLUMN "ganxu"."user"."avatar_url" IS '头像URL';
COMMENT ON COLUMN "ganxu"."user"."phone" IS '手机';
COMMENT ON COLUMN "ganxu"."user"."password" IS '密码';
COMMENT ON COLUMN "ganxu"."user"."version" IS '版本（更新锁）';
COMMENT ON COLUMN "ganxu"."user"."delete_status" IS '删除状态：0-未删除，其它删除';
COMMENT ON COLUMN "ganxu"."user"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."user" IS '用户表';

-- ----------------------------
-- Table structure for user_account_log
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_account_log";
CREATE TABLE "ganxu"."user_account_log" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "source" int2,
  "source_no" int8 NOT NULL,
  "amount" int8 NOT NULL,
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user_account_log"."id" IS '用户账户变动日志表ID';
COMMENT ON COLUMN "ganxu"."user_account_log"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."user_account_log"."source" IS '变动来源：：1订单，2退货单';
COMMENT ON COLUMN "ganxu"."user_account_log"."source_no" IS '来源ID，如订单编号';
COMMENT ON COLUMN "ganxu"."user_account_log"."amount" IS '变动金额';
COMMENT ON COLUMN "ganxu"."user_account_log"."create_time" IS '创建时间';
COMMENT ON TABLE "ganxu"."user_account_log" IS '用户余额变动表';

-- ----------------------------
-- Table structure for user_addr
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_addr";
CREATE TABLE "ganxu"."user_addr" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "zip" varchar(255) COLLATE "pg_catalog"."default",
  "province" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "city" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "district" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "address" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "default_addr " varchar(255) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1,
  "create_time" timestamp(6),
  "upate_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user_addr"."id" IS '用户地址表主键';
COMMENT ON COLUMN "ganxu"."user_addr"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."user_addr"."zip" IS '邮编';
COMMENT ON COLUMN "ganxu"."user_addr"."province" IS '省';
COMMENT ON COLUMN "ganxu"."user_addr"."city" IS '市';
COMMENT ON COLUMN "ganxu"."user_addr"."district" IS '区';
COMMENT ON COLUMN "ganxu"."user_addr"."address" IS '地址';
COMMENT ON COLUMN "ganxu"."user_addr"."default_addr " IS '是否默认地址';
COMMENT ON COLUMN "ganxu"."user_addr"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."user_addr"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."user_addr" IS '用户地址表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_info";
CREATE TABLE "ganxu"."user_info" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "real_name" varchar(255) COLLATE "pg_catalog"."default",
  "id_type" int2,
  "id_no" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "gender" int2,
  "points" int8,
  "register_time" timestamp(6),
  "birthday" date,
  "user_level" int2,
  "user_type" int2,
  "account_amount" int8,
  "version" int8 DEFAULT 0,
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user_info"."id" IS '用户信息表主键';
COMMENT ON COLUMN "ganxu"."user_info"."user_id" IS '用户表主键';
COMMENT ON COLUMN "ganxu"."user_info"."real_name" IS '真是姓名';
COMMENT ON COLUMN "ganxu"."user_info"."id_type" IS '证件类型';
COMMENT ON COLUMN "ganxu"."user_info"."id_no" IS '证件号';
COMMENT ON COLUMN "ganxu"."user_info"."email" IS '邮箱';
COMMENT ON COLUMN "ganxu"."user_info"."gender" IS '性别';
COMMENT ON COLUMN "ganxu"."user_info"."points" IS '积分';
COMMENT ON COLUMN "ganxu"."user_info"."register_time" IS '注册时间';
COMMENT ON COLUMN "ganxu"."user_info"."birthday" IS '生日';
COMMENT ON COLUMN "ganxu"."user_info"."user_level" IS '会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石';
COMMENT ON COLUMN "ganxu"."user_info"."user_type" IS '用户类型';
COMMENT ON COLUMN "ganxu"."user_info"."account_amount" IS '账户余额';
COMMENT ON COLUMN "ganxu"."user_info"."version" IS '版本（更新锁）';
COMMENT ON COLUMN "ganxu"."user_info"."update_time" IS '更新时间';

-- ----------------------------
-- Table structure for user_level
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_level";
CREATE TABLE "ganxu"."user_level" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "level_name " varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "min_point" int8 NOT NULL,
  "max_point" int8 NOT NULL,
  "create_time" timestamp(6),
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "level_code" int2 NOT NULL
)
;
COMMENT ON COLUMN "ganxu"."user_level"."id" IS '用户级别表主键';
COMMENT ON COLUMN "ganxu"."user_level"."level_name " IS '等级名称';
COMMENT ON COLUMN "ganxu"."user_level"."min_point" IS '该级别最低积分';
COMMENT ON COLUMN "ganxu"."user_level"."max_point" IS '该级别最高积分';
COMMENT ON COLUMN "ganxu"."user_level"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."user_level"."update_time" IS '更新时间';
COMMENT ON COLUMN "ganxu"."user_level"."level_code" IS '会员级别：1 普通会员，2 青铜，3白银，4黄金，5钻石';
COMMENT ON TABLE "ganxu"."user_level" IS '用户级别表';

-- ----------------------------
-- Table structure for user_login_log
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_login_log";
CREATE TABLE "ganxu"."user_login_log" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "login_time" timestamp(6) NOT NULL,
  "client_ip" varchar(255) COLLATE "pg_catalog"."default",
  "user_agent" varchar(512) COLLATE "pg_catalog"."default",
  "login_status" int2 DEFAULT 0
)
;
COMMENT ON COLUMN "ganxu"."user_login_log"."id" IS '用户登录日志表ID';
COMMENT ON COLUMN "ganxu"."user_login_log"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."user_login_log"."login_time" IS '登录时间';
COMMENT ON COLUMN "ganxu"."user_login_log"."client_ip" IS '登录IP';
COMMENT ON COLUMN "ganxu"."user_login_log"."user_agent" IS '用户代理';
COMMENT ON COLUMN "ganxu"."user_login_log"."login_status" IS '登录状态：0-成功';
COMMENT ON TABLE "ganxu"."user_login_log" IS '用户登录日志表';

-- ----------------------------
-- Table structure for user_oauths
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_oauths";
CREATE TABLE "ganxu"."user_oauths" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "oauth_type" int2 NOT NULL,
  "oauth_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "unionid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "credential" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6)
)
;
COMMENT ON COLUMN "ganxu"."user_oauths"."user_id" IS 'users表主键';
COMMENT ON COLUMN "ganxu"."user_oauths"."oauth_type" IS '1、微信，2、QQ，3、支付宝，4、其他';
COMMENT ON COLUMN "ganxu"."user_oauths"."oauth_id" IS '第三方 uid 、openid 等';
COMMENT ON COLUMN "ganxu"."user_oauths"."unionid" IS 'QQ / 微信同一主体下 Unionid 相同';
COMMENT ON COLUMN "ganxu"."user_oauths"."credential" IS '密码凭证 /access_token (目前更多是存储在缓存里)';
COMMENT ON COLUMN "ganxu"."user_oauths"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."user_oauths"."update_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."user_oauths" IS '用户鉴权表';

-- ----------------------------
-- Table structure for user_point_log
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."user_point_log";
CREATE TABLE "ganxu"."user_point_log" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "user_id" int8 NOT NULL,
  "point_source" int2,
  "refer_id" int8,
  "change_point" int8 NOT NULL,
  "create_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."user_point_log"."id" IS '用户积分日志表主键';
COMMENT ON COLUMN "ganxu"."user_point_log"."user_id" IS '用户ID';
COMMENT ON COLUMN "ganxu"."user_point_log"."point_source" IS '积分来源：0订单，1登陆，2活动';
COMMENT ON COLUMN "ganxu"."user_point_log"."refer_id" IS '积分来源ID：订单ID等';
COMMENT ON COLUMN "ganxu"."user_point_log"."change_point" IS '变更积分数';
COMMENT ON COLUMN "ganxu"."user_point_log"."create_time" IS '创建时间';
COMMENT ON TABLE "ganxu"."user_point_log" IS '用户积分日志表';

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS "ganxu"."warehouse";
CREATE TABLE "ganxu"."warehouse" (
  "id" int8 NOT NULL DEFAULT nextval('"ganxu".ganxu_common_seq'::regclass),
  "warehouse_sn" varchar(255) COLLATE "pg_catalog"."default",
  "warehoust_name" varchar(255) COLLATE "pg_catalog"."default",
  "warehouse_phone" varchar(255) COLLATE "pg_catalog"."default",
  "contact" varchar(255) COLLATE "pg_catalog"."default",
  "province" varchar(64) COLLATE "pg_catalog"."default",
  "city" varchar(64) COLLATE "pg_catalog"."default",
  "district" varchar(64) COLLATE "pg_catalog"."default",
  "address" varchar(512) COLLATE "pg_catalog"."default",
  "warehouse_status" int2 NOT NULL DEFAULT 1,
  "create_time" timestamp(6) NOT NULL,
  "upate_time" timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "ganxu"."warehouse"."id" IS '仓库信息表id';
COMMENT ON COLUMN "ganxu"."warehouse"."warehouse_sn" IS '仓库编码';
COMMENT ON COLUMN "ganxu"."warehouse"."warehoust_name" IS '仓库名称';
COMMENT ON COLUMN "ganxu"."warehouse"."warehouse_phone" IS '仓库电话';
COMMENT ON COLUMN "ganxu"."warehouse"."contact" IS '仓库联系人';
COMMENT ON COLUMN "ganxu"."warehouse"."province" IS '省';
COMMENT ON COLUMN "ganxu"."warehouse"."city" IS '市';
COMMENT ON COLUMN "ganxu"."warehouse"."district" IS '区';
COMMENT ON COLUMN "ganxu"."warehouse"."address" IS '地址';
COMMENT ON COLUMN "ganxu"."warehouse"."warehouse_status" IS '仓库状态：0禁用，1启用';
COMMENT ON COLUMN "ganxu"."warehouse"."create_time" IS '创建时间';
COMMENT ON COLUMN "ganxu"."warehouse"."upate_time" IS '更新时间';
COMMENT ON TABLE "ganxu"."warehouse" IS '仓库信息表';

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"ganxu"."ganxu_common_seq"', 1001, false);

-- ----------------------------
-- Primary Key structure for table order
-- ----------------------------
ALTER TABLE "ganxu"."order" ADD CONSTRAINT "order_master_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table order_cart
-- ----------------------------
ALTER TABLE "ganxu"."order_cart" ADD CONSTRAINT "order_cart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table order_detail
-- ----------------------------
ALTER TABLE "ganxu"."order_detail" ADD CONSTRAINT "order_detail_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_brand
-- ----------------------------
ALTER TABLE "ganxu"."product_brand" ADD CONSTRAINT "product_brand_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_category
-- ----------------------------
ALTER TABLE "ganxu"."product_category" ADD CONSTRAINT "product_category_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_comment
-- ----------------------------
ALTER TABLE "ganxu"."product_comment" ADD CONSTRAINT "product_comment_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_pic
-- ----------------------------
ALTER TABLE "ganxu"."product_pic" ADD CONSTRAINT "product_pic_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_stock
-- ----------------------------
ALTER TABLE "ganxu"."product_stock" ADD CONSTRAINT "warehouse_product_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table product_supplier
-- ----------------------------
ALTER TABLE "ganxu"."product_supplier" ADD CONSTRAINT "product_supplier_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table shipping_info
-- ----------------------------
ALTER TABLE "ganxu"."shipping_info" ADD CONSTRAINT "shipping_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table user
-- ----------------------------
CREATE UNIQUE INDEX "index_users_phone" ON "ganxu"."user" USING btree (
  "phone" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
) WHERE delete_status = 0;
CREATE UNIQUE INDEX "index_users_user_name" ON "ganxu"."user" USING btree (
  "user_name" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
) WHERE delete_status = 0;

-- ----------------------------
-- Primary Key structure for table user
-- ----------------------------
ALTER TABLE "ganxu"."user" ADD CONSTRAINT "users_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_account_log
-- ----------------------------
ALTER TABLE "ganxu"."user_account_log" ADD CONSTRAINT "user_account_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_addr
-- ----------------------------
ALTER TABLE "ganxu"."user_addr" ADD CONSTRAINT "user_addr_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "ganxu"."user_info" ADD CONSTRAINT "user_info_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_level
-- ----------------------------
ALTER TABLE "ganxu"."user_level" ADD CONSTRAINT "user_level_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_login_log
-- ----------------------------
ALTER TABLE "ganxu"."user_login_log" ADD CONSTRAINT "user_login_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table user_oauths
-- ----------------------------
ALTER TABLE "ganxu"."user_oauths" ADD CONSTRAINT "c_oauth_id" UNIQUE ("oauth_type", "oauth_id");
COMMENT ON CONSTRAINT "c_oauth_id" ON "ganxu"."user_oauths" IS 'oauth_id唯一约束';

-- ----------------------------
-- Primary Key structure for table user_oauths
-- ----------------------------
ALTER TABLE "ganxu"."user_oauths" ADD CONSTRAINT "oauths_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table user_point_log
-- ----------------------------
ALTER TABLE "ganxu"."user_point_log" ADD CONSTRAINT "user_point_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table warehouse
-- ----------------------------
ALTER TABLE "ganxu"."warehouse" ADD CONSTRAINT "warehouse_pkey" PRIMARY KEY ("id");
