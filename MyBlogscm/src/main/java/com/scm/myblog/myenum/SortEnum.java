package com.scm.myblog.myenum;

public enum SortEnum {

    Article_ASC_CreateTime{
        @Override
        public String getName() {
            return "时间顺序";
        }

        @Override
        public Integer getCode() {
            return 1;
        }
    },
    Article_DESC_CreateTime{
        @Override
        public String getName() {
            return "时间逆序";
        }

        @Override
        public Integer getCode() {
            return 2;
        }
    },

    Article_DESC_DianZanCount{
        @Override
        public String getName() {
            return "点赞顺序";
        }

        @Override
        public Integer getCode() {
            return 3;
        }
    },

    Article_ASC_DianZanCount{
        @Override
        public String getName() {
            return "点赞逆序";
        }

        @Override
        public Integer getCode() {
            return 4;
        }
    },

    Article_DESC_ShouCangCount{
        @Override
        public String getName() {
            return "收藏逆序";
        }

        @Override
        public Integer getCode() {
            return 5;
        }
    },
    Article_ASC_ShouCangCount{
        @Override
        public String getName() {
            return "收藏顺序";
        }

        @Override
        public Integer getCode() {
            return 6;
        }
    };

    public abstract String getName();
    public abstract Integer getCode();
}
