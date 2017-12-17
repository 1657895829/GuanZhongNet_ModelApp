package com.app.homework.bean;

import java.util.List;

/**
 * http://api.tianapi.com/military/?key=18e883dd6b316eb1d97fd86338abbf06&num=10
 */

public class JunShiBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2017-06-09 13:24","title":"六日战争祭：以色列打垮中东3大强国","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170609/001aa0bce2741aa4775b2e.jpg","url":"http://military.china.com.cn/2017-06/09/content_40997326.htm"},{"ctime":"2017-06-11 08:37","title":"海拔5000多米的边防巡逻，战士趴小溪边饮水","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170611/7427ea210a4d1aa6d7bd55.jpg","url":"http://military.china.com.cn/2017-06/11/content_41005011.htm"},{"ctime":"2017-06-11 08:47","title":"中国海军远航访问编队抵达巴基斯坦访问","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170611/7427ea210a4d1aa6da274f.jpg","url":"http://military.china.com.cn/2017-06/11/content_41005100.htm"},{"ctime":"2017-06-14 15:42","title":"酷似海豹部队！俄首曝车臣绝密特战队","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170614/001aa0bce2741aab2f432c.jpg","url":"http://military.china.com.cn/2017-06/14/content_41026084.htm"},{"ctime":"2017-06-23 09:34","title":"首开斩获！美\u201c超级大黄蜂\u201d击落叙苏-22战机","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170623/001aa0bce2741ab6b6de39.jpg","url":"http://military.china.com.cn/2017-06/23/content_41083346.htm"},{"ctime":"2017-07-06 09:11","title":"低空寂静杀手！美阿帕奇装高能激光炮灭敌","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170706/001aa0bce2741ac7d5182b.jpg","url":"http://military.china.com.cn/2017-07/06/content_41162611.htm"},{"ctime":"2017-07-09 07:18","title":"辽宁舰向公众开放","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170709/ac9e174e11811acbaf1913.jpg","url":"http://military.china.com.cn/2017-07/09/content_41180260.htm"},{"ctime":"2017-07-12 09:48","title":"独家航拍！辽宁舰驶离香港","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170712/ac9e174e11811acfc62563.jpg","url":"http://military.china.com.cn/2017-07/12/content_41199038.htm"},{"ctime":"2017-07-13 07:16","title":"第一个抗日根据地\u2014\u2014晋察冀抗日根据地","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170713/ac9e174e11811ad0f41105.jpg","url":"http://military.china.com.cn/2017-07/13/content_41205054.htm"},{"ctime":"2017-07-18 07:43","title":"这群高颜值中国女兵，又一次惊艳了我们！","description":"军事图集","picUrl":"http://images.china.cn/attachement/jpg/site1000/20170718/001aa0bce2741ad7921a46.jpg","url":"http://military.china.com.cn/2017-07/18/content_41233631.htm"}]
     */

    private int code;
    private String msg;
    private List<NewslistBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistBean {
        /**
         * ctime : 2017-06-09 13:24
         * title : 六日战争祭：以色列打垮中东3大强国
         * description : 军事图集
         * picUrl : http://images.china.cn/attachement/jpg/site1000/20170609/001aa0bce2741aa4775b2e.jpg
         * url : http://military.china.com.cn/2017-06/09/content_40997326.htm
         */

        private String ctime;
        private String title;
        private String description;
        private String picUrl;
        private String url;

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "NewslistBean{" +
                    "ctime='" + ctime + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", picUrl='" + picUrl + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
