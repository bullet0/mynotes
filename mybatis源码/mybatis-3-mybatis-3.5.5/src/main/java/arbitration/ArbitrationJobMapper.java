package arbitration;

import arbitration.ArbitrationJob;
import arbitration.ArbitrationJobExample;
import arbitration.ArbitrationJobWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArbitrationJobMapper {
    int countByExample(ArbitrationJobExample example);

    int deleteByExample(ArbitrationJobExample example);

    int deleteByPrimaryKey(String id);

    int insert(ArbitrationJobWithBLOBs record);

    int insertSelective(ArbitrationJobWithBLOBs record);

    List<ArbitrationJobWithBLOBs> selectByExampleWithBLOBs(ArbitrationJobExample example);

    List<ArbitrationJob> selectByExample(ArbitrationJobExample example);

    ArbitrationJobWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ArbitrationJobWithBLOBs record, @Param("example") ArbitrationJobExample example);

    int updateByExampleWithBLOBs(@Param("record") ArbitrationJobWithBLOBs record, @Param("example") ArbitrationJobExample example);

    int updateByExample(@Param("record") ArbitrationJob record, @Param("example") ArbitrationJobExample example);

    int updateByPrimaryKeySelective(ArbitrationJobWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArbitrationJobWithBLOBs record);

    int updateByPrimaryKey(ArbitrationJob record);
}