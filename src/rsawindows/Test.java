package rsawindows;

import java.util.HashMap;

import rsawindows.Bigint;

public class Test {
	public static void main(String[] args) {
	byte[] e1=Bigint.setBignum("703723231");
	byte[] e2=Bigint.setBignum("479");
	HashMap<byte[],Integer> map=new HashMap<>();
//	byte[] n=Bigint.setBignum("");
	for(int i=1;i<999999999;i++){
		byte[] tmp=Bigint.bignummodexp_M(e1, Bigint.inttobignum(i), e2);
		if(map.containsKey(tmp)){
			Bigint.bignumprint(tmp);
		}
		else{
			map.put(tmp, 1);
		}
	}
	
//	Bigint.bignumprint((Bigint.bignummulinver(e1,e2)));
//	int t=962640489;
//	System.out.println(t%261445);
	
	}
}
