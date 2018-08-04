package king.bird.marrykotlin.bean

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ImageBean {
    lateinit var name:String
    lateinit var imageUrl:String

    constructor(name: String, imageUrl: String) {
        this.name = name
        this.imageUrl = imageUrl
    }
}