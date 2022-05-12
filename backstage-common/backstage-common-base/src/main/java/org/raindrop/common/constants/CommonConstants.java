
package org.raindrop.common.constants;

/**
 * 公共常量
 *
 * @Author raindrop
 * @Date 2022/5/6
 **/
public interface CommonConstants {
    /**
     * header 中版本信息
     */
    String VERSION = "VERSION";


    /**
     * 删除
     */
    Integer STATUS_DEL = 1;

    /**
     * 正常
     */
    Integer STATUS_NORMAL = 0;

    /**
     * 锁定
     */
    Integer STATUS_LOCK = 9;

    /**
     * 菜单树根节点
     */
    Long MENU_TREE_ROOT_ID = -1L;

    /**
     * 编码
     */
    String UTF8 = "UTF-8";
    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 默认存储bucket
     */
    String BUCKET_NAME = "raindrop";

}
