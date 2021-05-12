package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class BuildedSqlMapClient {
	private static SqlMapClient smc;
	
	static  {
		Reader r = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			r = Resources.getResourceAsReader("sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(r);
			
		}catch (IOException e) {
			throw new RuntimeException("SqlMapClient 객체 생성 실패 -" + e, e);
		}finally {
			if(r!=null) try{r.close();}catch(IOException e) {e.printStackTrace();}
		}
	}
	
	public static SqlMapClient getSqlMapClient() {
		if(smc==null) {
			
		}
		return smc;
	}
}
