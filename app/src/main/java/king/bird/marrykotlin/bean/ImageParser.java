package king.bird.marrykotlin.bean;

import java.util.List;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ImageParser {

    private List<ResultListBean> resultList;

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    public static class ResultListBean {
        /**
         * id : 1533284165764
         * name : 性感甜美颜值妹子丰满白嫩的蜜桃美臀
         * imageUrl : http://fm.shiyunjj.com/small/2017/1039.jpg
         */

        private String id;
        private String name;
        private String imageUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
