<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${customPackage.boDao}.${entity}BoMapper">

<#if baseResultMap>
    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${customPackage.bo}.${entity}" extends="${customPackage.poDao}.${table.mapperName}.BaseResultMap">
    </resultMap>
</#if>
</mapper>
