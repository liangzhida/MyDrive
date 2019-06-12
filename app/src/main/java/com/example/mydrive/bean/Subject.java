package com.example.mydrive.bean;

import java.util.List;

public class Subject {

    /**
     * retCode : 200
     * msg : success
     * result : {"curPage":1,"list":[{"a":"保持原状态行驶","b":"加速行驶","c":"迅速停车让行","d":"降速靠右让路","explainText":"试题解释：和谐第一，人家要超车就减速靠右给他让出超车的位置。","file":"","id":"1631","tikuType":"select","title":"在没有中心线的道路上发现后车发出超车信号时，如果条件许可如何行驶？","val":"4"},{"a":"禁止借道","b":"禁止变道","c":"禁止超车","d":"禁止掉头","explainText":"试题解释：禁止超车：表示该标志至前方解除禁止超车标志的路段内，不准机动车超车。此标志设在禁止超车的起点。","file":"http://f2.mob.com/imgs/2016/06/28/PIA/K5ZCOPIM6KXU6IRH6ZHA_250x250.jpg","id":"1997","tikuType":"select","title":"这个标志是何含义？","val":"3"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：黄灯闪烁是警示的意思，没有不能右转的意思。","file":"http://f2.mob.com/imgs/2016/06/28/QYQ/K5ZCOQYM6KXU6IRH6ZHQ_600x238.jpg","id":"1910","tikuType":"select","title":"驾驶机动车在前方路口不能右转弯。","val":"2"},{"a":"窄路","b":"右侧变窄","c":"左侧变窄","d":"窄桥","explainText":"试题解释：窄桥：用以警告车辆驾驶人注意前方桥面宽度变窄，应谨慎驾驶。设在桥面净宽较两端路面宽度变窄，且桥的净宽小于6m的桥梁以前适当位置。","file":"http://f2.mob.com/imgs/2016/06/28/RYA/K5ZCORYM6KXU6IRH6ZIA_274x250.jpg","id":"1941","tikuType":"select","title":"这个标志是何含义？","val":"4"},{"a":"堤坝路","b":"临崖路","c":"易滑路","d":"傍水路","explainText":"试题解释：堤坝路：用以提醒车辆驾驶人小心驾驶，设在沿水库、湖泊、河流等堤坝道路以前适当位置。","file":"http://f2.mob.com/imgs/2016/06/28/RYQ/K5ZCORYM6KXU6IRH6ZIQ_279x250.jpg","id":"1952","tikuType":"select","title":"这个标志是何含义？","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：我觉得答案是正确的,因为如果突然爆胎,瞬间靠右,会导致与后方来车相撞.所以只能先保持直线行驶,再减速停车.","file":"","id":"2393","tikuType":"select","title":"车辆后轮胎爆裂，车尾会摇摆不定，驾驶人应双手紧握转向盘，控制车辆保持直线行驶，减速停车。","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：是对的！谨慎驾驶有塌方就避免停车！！！又不是说不能停车，只是避免！","file":"","id":"2359","tikuType":"select","title":"通过山区危险路段，尤其是通过经常发生塌方、泥石流的山区地段，应谨慎驾驶，避免停车。","val":"1"},{"a":"以正常速度行驶","b":"持续鸣喇叭示意其让道","c":"加速绕行","d":"提前鸣喇叭，并适当降低车速","explainText":"试题解释：只有C最和谐。","file":"","id":"2288","tikuType":"select","title":"雨天行车，遇撑雨伞和穿雨衣的行人在公路上行走时，应怎样做？","val":"4"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：超车时应尽且提高辆车速度差，以减少超车距离和时间。超越时间加长，危险性也会增大；并行容易引起车祸。","file":"http://f2.mob.com/imgs/2016/06/28/SAQ/K5ZCOSAM6KXU6IRH6ZJQ_622x250.jpg","id":"4959","tikuType":"select","title":"如图所示，当您超越右侧车辆时，应该尽快超越，减少并行时间。","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：和谐第一，做题就要假扮圣人，人家要超车就减速靠右给他让出超车的位置。","file":"","id":"4987","tikuType":"select","title":"遇后车超车时，在条件许可的情况下应减速靠右让路，是为了给后车留出超车空间。","val":"1"}],"total":1311}
     */

    private String retCode;
    private String msg;
    private ResultBean result;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * curPage : 1
         * list : [{"a":"保持原状态行驶","b":"加速行驶","c":"迅速停车让行","d":"降速靠右让路","explainText":"试题解释：和谐第一，人家要超车就减速靠右给他让出超车的位置。","file":"","id":"1631","tikuType":"select","title":"在没有中心线的道路上发现后车发出超车信号时，如果条件许可如何行驶？","val":"4"},{"a":"禁止借道","b":"禁止变道","c":"禁止超车","d":"禁止掉头","explainText":"试题解释：禁止超车：表示该标志至前方解除禁止超车标志的路段内，不准机动车超车。此标志设在禁止超车的起点。","file":"http://f2.mob.com/imgs/2016/06/28/PIA/K5ZCOPIM6KXU6IRH6ZHA_250x250.jpg","id":"1997","tikuType":"select","title":"这个标志是何含义？","val":"3"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：黄灯闪烁是警示的意思，没有不能右转的意思。","file":"http://f2.mob.com/imgs/2016/06/28/QYQ/K5ZCOQYM6KXU6IRH6ZHQ_600x238.jpg","id":"1910","tikuType":"select","title":"驾驶机动车在前方路口不能右转弯。","val":"2"},{"a":"窄路","b":"右侧变窄","c":"左侧变窄","d":"窄桥","explainText":"试题解释：窄桥：用以警告车辆驾驶人注意前方桥面宽度变窄，应谨慎驾驶。设在桥面净宽较两端路面宽度变窄，且桥的净宽小于6m的桥梁以前适当位置。","file":"http://f2.mob.com/imgs/2016/06/28/RYA/K5ZCORYM6KXU6IRH6ZIA_274x250.jpg","id":"1941","tikuType":"select","title":"这个标志是何含义？","val":"4"},{"a":"堤坝路","b":"临崖路","c":"易滑路","d":"傍水路","explainText":"试题解释：堤坝路：用以提醒车辆驾驶人小心驾驶，设在沿水库、湖泊、河流等堤坝道路以前适当位置。","file":"http://f2.mob.com/imgs/2016/06/28/RYQ/K5ZCORYM6KXU6IRH6ZIQ_279x250.jpg","id":"1952","tikuType":"select","title":"这个标志是何含义？","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：我觉得答案是正确的,因为如果突然爆胎,瞬间靠右,会导致与后方来车相撞.所以只能先保持直线行驶,再减速停车.","file":"","id":"2393","tikuType":"select","title":"车辆后轮胎爆裂，车尾会摇摆不定，驾驶人应双手紧握转向盘，控制车辆保持直线行驶，减速停车。","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：是对的！谨慎驾驶有塌方就避免停车！！！又不是说不能停车，只是避免！","file":"","id":"2359","tikuType":"select","title":"通过山区危险路段，尤其是通过经常发生塌方、泥石流的山区地段，应谨慎驾驶，避免停车。","val":"1"},{"a":"以正常速度行驶","b":"持续鸣喇叭示意其让道","c":"加速绕行","d":"提前鸣喇叭，并适当降低车速","explainText":"试题解释：只有C最和谐。","file":"","id":"2288","tikuType":"select","title":"雨天行车，遇撑雨伞和穿雨衣的行人在公路上行走时，应怎样做？","val":"4"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：超车时应尽且提高辆车速度差，以减少超车距离和时间。超越时间加长，危险性也会增大；并行容易引起车祸。","file":"http://f2.mob.com/imgs/2016/06/28/SAQ/K5ZCOSAM6KXU6IRH6ZJQ_622x250.jpg","id":"4959","tikuType":"select","title":"如图所示，当您超越右侧车辆时，应该尽快超越，减少并行时间。","val":"1"},{"a":"正确","b":"错误","c":"","d":"","explainText":"试题解释：和谐第一，做题就要假扮圣人，人家要超车就减速靠右给他让出超车的位置。","file":"","id":"4987","tikuType":"select","title":"遇后车超车时，在条件许可的情况下应减速靠右让路，是为了给后车留出超车空间。","val":"1"}]
         * total : 1311
         */

        private int curPage;
        private int total;
        private List<ListBean> list;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * a : 保持原状态行驶
             * b : 加速行驶
             * c : 迅速停车让行
             * d : 降速靠右让路
             * explainText : 试题解释：和谐第一，人家要超车就减速靠右给他让出超车的位置。
             * file :
             * id : 1631
             * tikuType : select
             * title : 在没有中心线的道路上发现后车发出超车信号时，如果条件许可如何行驶？
             * val : 4
             */

            private String a;
            private String b;
            private String c;
            private String d;
            private String explainText;
            private String file;
            private String id;
            private String tikuType;
            private String title;
            private String val;

            public String getA() {
                return a;
            }

            public void setA(String a) {
                this.a = a;
            }

            public String getB() {
                return b;
            }

            public void setB(String b) {
                this.b = b;
            }

            public String getC() {
                return c;
            }

            public void setC(String c) {
                this.c = c;
            }

            public String getD() {
                return d;
            }

            public void setD(String d) {
                this.d = d;
            }

            public String getExplainText() {
                return explainText;
            }

            public void setExplainText(String explainText) {
                this.explainText = explainText;
            }

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTikuType() {
                return tikuType;
            }

            public void setTikuType(String tikuType) {
                this.tikuType = tikuType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getVal() {
                return val;
            }

            public void setVal(String val) {
                this.val = val;
            }
        }
    }
}
