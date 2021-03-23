package com.hutu.boot.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hutu.boot.vo.index.IndexVO;
import com.hutu.common.entity.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;


@Slf4j
@Api(tags = "首页")
@RestController
@RequestMapping("index")
public class IndexController {

    @GetMapping("type/1")
    public static R getType1List() {
        JSONArray json = JSONUtil.parseArray("[{\n" +
                "      id: 1,\n" +
                "      title: \"轮播图\",\n" +
                "      type: 0, \n" +
                "      sortOrder: 1, \n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0, \n" +
                "        title: \"\",\n" +
                "        type: 0, \n" +
                "        picUrl: \"https://resource.smartisan.com/resource/fda5c3e61a71c0f883bbd6c76516cd85.png\"\n" +
                "      }, {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        title: \"\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/w/white25wap.png\"\n" +
                "      }, {\n" +
                "        id: 3,\n" +
                "        relateId: 0,\n" +
                "        title: \"\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/9402b4117bf1c1754dca08b52c98cca0.png\"\n" +
                "      }, {\n" +
                "        id: 4,\n" +
                "        relateId: 0,\n" +
                "        title: \"\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/B/BS2000WAP.png\"\n" +
                "      }]\n" +
                "\n" +
                "    }, {\n" +
                "\n" +
                "      id: 2,\n" +
                "      title: \"频道图标\",\n" +
                "      type: 1, \n" +
                "      sortOrder: 2,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        title: \"IPhone XS\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/5224f868cca31a8b913411ff9d69dcaf.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        title: \"购买空净\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/e98cfb0a63e8b8c80e5b87ca66bda64c.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 3,\n" +
                "        relateId: 0,\n" +
                "        title: \"新品配件\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://i.loli.net/2019/06/23/5d0f7e938c57070713.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 4,\n" +
                "        relateId: 0,\n" +
                "        title: \"服装\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/75892aebb63f998fa9b37e9a18984a98.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 5,\n" +
                "        relateId: 0,\n" +
                "        title: \"更多\",\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://i.loli.net/2019/06/23/5d0f7e938cbee56873.png\"\n" +
                "      }\n" +
                "      ]\n" +
                "\n" +
                "    }, {\n" +
                "\n" +
                "      id: 3,\n" +
                "      title: \"热门商品\",\n" +
                "      type: 2,\n" +
                "      relateId: 1,\n" +
                "      sortOrder: 3,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        title: \"各色DNA检测套装\",\n" +
                "        description: \"千万级基因位点数据解读\",\n" +
                "        price: 499.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/9bffe702b1f0aea221b1f18ddf886958.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        title: \"畅呼吸智能空气净化器\",\n" +
                "        description: \"超强净化能力，超低噪音\",\n" +
                "        price: 1299.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/6ff92d05a3bfab4fad489ca04d3eea5a.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 3,\n" +
                "        relateId: 0,\n" +
                "        title: \"坚果 Pro 2\",\n" +
                "        description: \"漂亮得不像实力派\",\n" +
                "        price: 1799.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/c71ce2297b362f415f1e74d56d867aed.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 4,\n" +
                "        relateId: 0,\n" +
                "        title: \"Smartisan 帆布鞋\",\n" +
                "        description: \"一双踏实、舒适的帆布鞋\",\n" +
                "        price: 199.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/578116bddf1d170c89e9af7ba5073fb6.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 5,\n" +
                "        relateId: 0,\n" +
                "        title: \"卫衣 开衫 热血\",\n" +
                "        description: \"风格简洁、舒适服帖\",\n" +
                "        price: 299.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/ff8106afb5fe0ed8ec23e3594766b4f7.png\"\n" +
                "      }\n" +
                "      ]\n" +
                "    }, {\n" +
                "      id: 4,\n" +
                "      title: \"\",\n" +
                "      type: 3,\n" +
                "      sortOrder: 4,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/e/erfenzhiyiqingrenjieapp.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/s/shangchengguanggaowei.png\"\n" +
                "      }\n" +
                "      ]\n" +
                "    }, {\n" +
                "      id: 5,\n" +
                "      title: \"官方精选\",\n" +
                "      type: 4,\n" +
                "      relateId: 2,\n" +
                "      sortOrder: 5,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        title: \"坚果 Pro\",\n" +
                "        description: \"漂亮得不像实力派\",\n" +
                "        price: 1299.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/3bd5267edd7257e719e7965b756e2c2e.png\",\n" +
                "        tag: \"满减\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        title: \"半入耳式耳机 心动版\",\n" +
                "        description: \"哑光表面、专业级调音\",\n" +
                "        price: 99.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/90be7779c2454407ee5f4b6184c929ed.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 3,\n" +
                "        relateId: 0,\n" +
                "        title: \"坚果 Pro 2 软胶保护套\",\n" +
                "        description: \"TPU 环保材质、完美贴合\",\n" +
                "        price: 49.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/ca332140775bb0646f65e516942d3adc.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 4,\n" +
                "        relateId: 0,\n" +
                "        title: \"Smartisan 半入耳式耳机\",\n" +
                "        description: \"经典配色、专业调音、高品质麦克风\",\n" +
                "        price: 59.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/8a875418797690e26b665cc0d86dffc7.jpg\"\n" +
                "      }\n" +
                "      ]\n" +
                "    }, {\n" +
                "\n" +
                "      id: 6,\n" +
                "      title: \"\",\n" +
                "      type: 3,\n" +
                "      sortOrder: 6,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/3a7c77edcd70a9d3621bc3e7d0fba1c1.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/4bf08546b25b1527cc715a9717db6327.png\"\n" +
                "      }\n" +
                "      ]\n" +
                "    }, {\n" +
                "\n" +
                "      id: 7,\n" +
                "      title: \"品牌周边\",\n" +
                "      type: 5,\n" +
                "      relateId: 3,\n" +
                "      sortOrder: 7,\n" +
                "      list: [{\n" +
                "        id: 1,\n" +
                "        relateId: 0,\n" +
                "        title: \"Smartisan T恤 薛定谔\",\n" +
                "        description: \"风格简洁、舒适服帖\",\n" +
                "        price: 149.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/22f9e824c1cf7e8fad3d432ee494b932.png\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 2,\n" +
                "        relateId: 0,\n" +
                "        title: \"记事本\",\n" +
                "        description: \"优质米色纸、不洇不透\",\n" +
                "        price: 49.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/4a38a3678f151ec9c022f5f676c2b7da.jpg\"\n" +
                "      }, {\n" +
                "        id: 3,\n" +
                "        relateId: 0,\n" +
                "        title: \"坚果砖式蓝牙小音箱\",\n" +
                "        description: \"一款设计出色、音质出众的随身音箱\",\n" +
                "        price: 149.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/c44f0ab4da5591fc3d0f82b7ac0f4f65.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 4,\n" +
                "        relateId: 0,\n" +
                "        title: \"坚果彩虹数据线\",\n" +
                "        description: \"七彩配色随机发货，为生活增添一份小小惊喜\",\n" +
                "        price: 19.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/82aab62886740f165a3631ce6cffe895.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 5,\n" +
                "        relateId: 0,\n" +
                "        title: \"明信片\",\n" +
                "        description: \"优质卡纸、包装精致、色彩饱满\",\n" +
                "        price: 9.90,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/5ff83a138b1186b0cdf2c76fee2b6e44.jpg\"\n" +
                "      },\n" +
                "      {\n" +
                "        id: 6,\n" +
                "        relateId: 0,\n" +
                "        title: \"任天堂发售红白机\",\n" +
                "        description: \"100% 美国 SUPIMA 棉、舒适拉绒质地\",\n" +
                "        price: 149.00,\n" +
                "        type: 0,\n" +
                "        picUrl: \"https://resource.smartisan.com/resource/804edf579887b3e1fae4e20a379be5b5.png\"\n" +
                "      }\n" +
                "      ]\n" +
                "    }]");
        List<IndexVO> indexVOS = JSONUtil.toList(json, IndexVO.class);
        return R.ok(indexVOS);
    }

    public static void main(String[] args) {
        getType1List();
    }

    @GetMapping("type/2")
    public R getType2List() {
        return R.ok();
    }

    @GetMapping("type/3")
    public R getType3List() {
        return R.ok();
    }

    @GetMapping("type/4")
    public R getType4List() {
        return R.ok();
    }

    @GetMapping("type/5")
    public R getType5List() {
        return R.ok();
    }
}
