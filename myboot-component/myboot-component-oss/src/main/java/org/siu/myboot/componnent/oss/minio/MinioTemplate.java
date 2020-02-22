package org.siu.myboot.componnent.oss.minio;

import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * MinioTemplate
 * <p>
 * 提供MinIo的基本操作API
 *
 * @Author Siu
 * @Date 2020/2/21 9:12
 * @Version 0.0.1
 */
@Component
public class MinioTemplate {

    @Autowired
    MinioClient minioClient;


    /**
     * 获取全部bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#listBuckets
     *
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidResponseException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    public List<Bucket> getAllBuckets() throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        return this.minioClient.listBuckets();
    }


    /**
     * 创建bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#makeBucket
     *
     * @param bucketName bucket名称
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidResponseException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     * @throws RegionConflictException
     */
    public void createBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        MinioClient client = this.minioClient;
        if (!client.bucketExists(bucketName)) {
            client.makeBucket(bucketName);
        }
    }


    /**
     * 删除bucket
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#removeBucket
     *
     * @param bucketName bucket名称
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidResponseException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    public void removeBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        this.minioClient.removeBucket(bucketName);
    }


    /**
     * 根据前缀获取文件
     *
     * @param bucketName bucket名称
     * @param prefix     前缀
     * @param recursive  是否递归
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    public List<Item> getAllObjectsByPrefix(String bucketName, String prefix, boolean recursive) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {

        List<Item> objectList = new ArrayList<>();
        Iterable<Result<Item>> objectsIterator = this.minioClient.listObjects(bucketName, prefix, recursive);
        while (objectsIterator.iterator().hasNext()) {
            objectList.add(objectsIterator.iterator().next().get());
        }
        return objectList;
    }


    /**
     * 获取文件外链
     * https://docs.minio.io/cn/java-client-api-reference.html#presignedGetObject
     * <p>
     * 生成一个给HTTP GET请求用的presigned URL。浏览器/移动端的客户端可以用这个URL进行下载，即使其所在的存储桶是私有的。这个presigned URL可以设置一个失效时间，默认值是7天
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param expires    失效时间（以秒为单位），默认是7天，不得大于七天。
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidExpiresRangeException
     * @throws InvalidResponseException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    public String getObjectURL(String bucketName, String objectName, int expires) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidExpiresRangeException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        return this.minioClient.presignedGetObject(bucketName, objectName, expires);
    }


    /**
     * 获取文件
     * https://docs.minio.io/cn/java-client-api-reference.html#getObject
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return
     * @throws IOException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InsufficientDataException
     * @throws InvalidArgumentException
     * @throws InvalidResponseException
     * @throws InternalException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws XmlPullParserException
     * @throws ErrorResponseException
     */
    public InputStream getObject(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        return this.minioClient.getObject(bucketName, objectName);

    }


    /**
     * 上传文件
     * https://docs.minio.io/cn/java-client-api-reference.html#putObject
     * <p>
     * 拿到流的数据，使用随机生成的content key进行加密，并上传到指定存储桶中。同时将加密后的content key和iv做为加密对象有header也上传到存储桶中。content key使用传入到该方法的master key进行加密
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @param stream     文件流
     * @throws IOException
     * @throws XmlPullParserException
     * @throws NoSuchAlgorithmException
     * @throws RegionConflictException
     * @throws InvalidKeyException
     * @throws InvalidPortException
     * @throws InvalidResponseException
     * @throws ErrorResponseException
     * @throws NoResponseException
     * @throws InvalidBucketNameException
     * @throws InsufficientDataException
     * @throws InvalidEndpointException
     * @throws InternalException
     * @throws InvalidArgumentException
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidPortException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InvalidEndpointException, InternalException, InvalidArgumentException {
        this.putObject(bucketName, objectName, stream, "application/octet-stream");

    }

    public void putObject(String bucketName, String objectName, InputStream stream, String contextType) throws IOException, XmlPullParserException, NoSuchAlgorithmException, RegionConflictException, InvalidKeyException, InvalidPortException, InvalidResponseException, ErrorResponseException, NoResponseException, InvalidBucketNameException, InsufficientDataException, InvalidEndpointException, InternalException, InvalidArgumentException {
        // 如果bucket不存在，创建
        createBucket(bucketName);
        this.minioClient.putObject(bucketName, objectName, stream, (long) stream.available(), null, null, contextType);

    }


    /**
     * 获取文件信息(对象的元数据)
     * <p>
     * https://docs.minio.io/cn/java-client-api-reference.html#statObject
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     * @return
     */
    public ObjectStat getObjectStat(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        return this.minioClient.statObject(bucketName, objectName);
    }


    /**
     * 删除文件
     * https://docs.minio.io/cn/java-client-api-reference.html#removeObject
     *
     * @param bucketName bucket名称
     * @param objectName 文件名称
     */
    public void removeObject(String bucketName, String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidArgumentException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException {
        this.minioClient.removeObject(bucketName, objectName);

    }


}
