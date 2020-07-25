package arbitration;

import arbitration.JobExtendPdfurl;
import arbitration.JobExtendPdfurlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobExtendPdfurlMapper {
    int countByExample(JobExtendPdfurlExample example);

    int deleteByExample(JobExtendPdfurlExample example);

    int deleteByPrimaryKey(String id);

    int insert(JobExtendPdfurl record);

    int insertSelective(JobExtendPdfurl record);

    List<JobExtendPdfurl> selectByExampleWithBLOBs(JobExtendPdfurlExample example);

    List<JobExtendPdfurl> selectByExample(JobExtendPdfurlExample example);

    JobExtendPdfurl selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") JobExtendPdfurl record, @Param("example") JobExtendPdfurlExample example);

    int updateByExampleWithBLOBs(@Param("record") JobExtendPdfurl record, @Param("example") JobExtendPdfurlExample example);

    int updateByExample(@Param("record") JobExtendPdfurl record, @Param("example") JobExtendPdfurlExample example);

    int updateByPrimaryKeySelective(JobExtendPdfurl record);

    int updateByPrimaryKeyWithBLOBs(JobExtendPdfurl record);

    int updateByPrimaryKey(JobExtendPdfurl record);
}