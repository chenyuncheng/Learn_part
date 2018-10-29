package com.wootion.cast.vehcert.utils;


import com.wootion.cast.vehcert.domain.*;
import com.wootion.cast.vehcert.service.EnterpriseService;
import org.apache.log4j.Logger;

import java.util.List;

public class InsertProductData {

    private static Logger logger = Logger.getLogger(InsertProductData.class);
    private EnterpriseService enterpriseService;
    public InsertProductData(EnterpriseService enterpriseService){

        this.enterpriseService = enterpriseService;
    }
    /**
     * 添加NGAVS数据（生产信息数据）
     * @param NGAVS_Str
     * @return
     */
    public boolean addNGAVSData(String NGAVS_Str){
        logger.info("######开始处理生产数据######");
        List<Enterprise> enterpriselist;
        try {
            enterpriselist = enterpriseService.getEnterprise(null);
            if (enterpriselist.size() == 0) {
                logger.info("还没有配置企业基础信息");
                return false;
            }
            // 解析字符串(得到3条生产数据信息：vin号，catecode，下线日期)
            String vin=NGAVS_Str.substring(3, 20);
            String catecode=NGAVS_Str.substring(20, 25);
            String colorcode=NGAVS_Str.substring(25, 26);
            String neicolorcode=NGAVS_Str.substring(27, 28);
            String factoryType=vin.substring(10, 11);

            String scrq = null;
            String scrqall = null;
            String factorycode = null;
            if (factoryType.equals("F")) {
                scrq = NGAVS_Str.substring(112, 120);
                scrqall = NGAVS_Str.substring(112, 128);
                factorycode = "BVT8A"; // 一厂工厂代码
            } else if (factoryType.equals("E")) {
                scrq = NGAVS_Str.substring(116, 124);
                scrqall = NGAVS_Str.substring(116, 132);
                factorycode = "BVT8C"; // 二厂工厂代码
            } else if (factoryType.equals("S")) {
                scrq = NGAVS_Str.substring(116, 124);
                scrqall = NGAVS_Str.substring(116, 132);
                factorycode = "BVT8E"; // 三厂工厂代码
            }else if (factoryType.equals("H")) {
                scrq = NGAVS_Str.substring(116, 124);
                scrqall = NGAVS_Str.substring(116, 132);
                factorycode = "GN4UA"; // 四厂工厂代码
            }else if(factoryType.equals("")){

            }
            //久的合格证数据
            List<Vehcert> oldlist = commonservice.getQueryObjects(Vehcert.class," vinCode='" + vin + "'",null, null);
            //合格证基础参数
            List<Hegeparam> paralist = commonservice.getQueryObjects(Hegeparam.class," cateCode='"+ catecode+ "' and ispass='1'",null, null);
            //颜色基础参数
            List<ColParam> ysparalist = commonservice.getQueryObjects(ColParam.class," cateCode='"+ catecode+ "' and colorCode='"+ colorcode + "'",null, null);
            //一致性基础参数
            List<Cocparam> cocparalist = commonservice.getQueryObjects(Cocparam.class," catcode like '%"+ catecode+ "%' and ispass='1'",null, null);
            //燃油上传基础参数
            List<Fuelupparam> upparam = commonservice.getQueryObjects(Fuelupparam.class," catecode like '%"+ catecode+ "%'", null,null);

			/*1.燃油上传数据入库*/




        } catch (DbException e) {
            e.printStackTrace();
        }


        return true;
    }

}
