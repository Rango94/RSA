package rsawindows;
import java.util.Arrays;

public class Bigint {

	// 随机生成bignum
	public static byte[] bignumrd(int length) {
		String str = null;
		for (int i = 0; i < length / 8 + 1; i++) {
			int a = (int) (Math.random() * 99999999 + 10000000);
			str = String.valueOf(a) + str;
		}
		str = str.substring(0, length);
		byte[] ans = setBignum(str);
		ans[ans.length - 1] = 1;
		return ans;
	}

	// String型转bignum型
	public static byte[] setBignum(String bignum) {
		int bigint_length = bignum.length();
		byte bignum_x[] = new byte[bigint_length];
		for (int i = 0; i < bigint_length; i++) {
			bignum_x[i] = (byte) Integer.parseInt(bignum.substring(i, i + 1));
		}
		return bignum_x;
	}

	// bignum转String
	public static String bignumtostr(byte[] bignum) {
		String str = null;
		for (int i = 0; i < bignum.length; i++) {
			str = str + bignum[i];
		}
		return str.substring(4, bignum.length + 4);
	}

	// bignum型打印方法
	public static void bignumprint(byte[] val) {
		int val_length = val.length;
		for (int i = 0; i < val_length; i++) {
			System.out.print(val[i]);
			if(i!=0&&i%100==0){
				System.out.println("");
			}
			if (i == val_length - 1) {
				System.out.println("");
			}
		}
	}

	// bignum型加法
	public static byte[] bignumaddS(String a, String b) {
		byte[] C = setBignum(a);
		byte[] D = setBignum(b);
		byte[] A = null;
		byte[] B = null;
		byte[] add;
		if (C.length < D.length) {
			A = setBignum(b);
			B = setBignum(a);
		} else {
			A = setBignum(a);
			B = setBignum(b);
		}
		int B_length = B.length;
		int A_length = A.length;
		int up = 0;
		for (int i = 0; i < B_length; i++) {
			int tmpadd = (A[A_length - 1 - i] + B[B_length - 1 - i]) + up;
			A[A_length - i - 1] = (byte) (tmpadd % 10);
			if (tmpadd >= 10) {
				up = 1;
			} else {
				up = 0;
			}
		}
		if (up == 1) {
			for (int i = A_length - B_length - 1; i >= 0; i--) {
				int tmpadd = A[i] + 1;
				A[i] = (byte) (tmpadd % 10);
				if (tmpadd < 10) {
					up = 0;
					break;
				}
			}
		}
		if (up == 1) {
			add = new byte[A_length + 1];
			add[0] = 1;
			for (int i = 1; i < A_length + 1; i++) {
				add[i] = A[i - 1];
			}
		} else
			add = A;
		return add;
	}

	// bignum型加法(参数为byte[]
	public static byte[] bignumadd(byte[] a, byte[] b) {
		byte[] A;
		byte[] B;
		byte[] add;
		if (a.length < b.length) {
			A = new byte[b.length];
			B = new byte[a.length];
			System.arraycopy(b, 0, A, 0, b.length);
			System.arraycopy(a, 0, B, 0, a.length);
		} else {
			A = new byte[a.length];
			B = new byte[b.length];
			System.arraycopy(a, 0, A, 0, a.length);
			System.arraycopy(b, 0, B, 0, b.length);
		}
		int B_length = B.length;
		int A_length = A.length;
		int up = 0;
		for (int i = 0; i < B_length; i++) {
			int tmpadd = (A[A_length - 1 - i] + B[B_length - 1 - i]) + up;
			A[A_length - i - 1] = (byte) (tmpadd % 10);
			if (tmpadd >= 10) {
				up = 1;
			} else {
				up = 0;
			}
		}
		if (up == 1) {
			for (int i = A_length - B_length - 1; i >= 0; i--) {
				int tmpadd = A[i] + 1;
				A[i] = (byte) (tmpadd % 10);
				if (tmpadd < 10) {
					up = 0;
					break;
				}
			}
		}
		if (up == 1) {
			add = new byte[A_length + 1];
			add[0] = 1;
			for (int i = 1; i < A_length + 1; i++) {
				add[i] = A[i - 1];
			}
		} else
			add = A;
		return add;
	}

	// 减法
	public static byte[] bignumminus(byte[] A, byte[] B) {
		if (Arrays.equals(A, B)) {

			return inttobignum(0);
		} else {
			int lend = 0;
			if (bigup(B, A)) {
				byte[] tmp = new byte[A.length];
				System.arraycopy(A, 0, tmp, 0, A.length);
				A = B;
				B = tmp;
			}
			byte[] C = new byte[A.length];
			for (int i = 0; i < B.length; i++) {
				int tmp = A[A.length - 1 - i] - lend - B[B.length - 1 - i];
				if (tmp < 0) {
					lend = 1;
					C[A.length - 1 - i] = (byte) (10 + tmp);
				} else {
					lend = 0;
					C[A.length - 1 - i] = (byte) tmp;
				}
			}
			for (int i = A.length - B.length - 1; i >= 0; i--) {
				if (lend == 1) {
					int tmp = (byte) (A[i] - 1);
					if (tmp < 0) {
						lend = 1;
						C[i] = (byte) 9;
					} else {
						lend = 0;
						C[i] = (byte) tmp;
					}
				} else {
					C[i] = A[i];
				}

			}
			int k = 0;
			for (int i = 0; i < A.length; i++) {
				if (C[i] == 0) {
					k++;
				} else
					break;
			}
			byte[] zero = { 0 };
			if (k == A.length) {
				return zero;
			} else {
				byte[] ans = subbignum0(C, k, A.length - k);
				return ans;
			}
		}
	}

	// 去掉前面的0
	public static byte[] bignumcut0(byte[] A) {
		int k = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				k++;
			} else
				break;
		}
		byte[] ans = subbignum0(A, k, A.length - k);
		return ans;
	}

	// 大数扩展
	public static byte[] bignumextend(byte[] A, byte b, int length) {
		byte ans[] = new byte[length + A.length];
		for (int i = 0; i < A.length; i++) {
			ans[i] = A[i];
		}
		for (int i = 0; i < length; i++) {
			ans[i + A.length] = b;
		}
		return ans;
	}

	// int型转bignum型
	public static byte[] inttobignum(int a) {
		String A = String.valueOf(a);
		byte[] B = setBignum(A);
		return B;
	}

	// bignum型转int型
	public static int bignumtoint(byte[] A) {
		int length = A.length;
		int ans = 0;
		for (int i = length - 1; i >= 0; i--) {
			ans = (int) (A[i] * Math.pow(10, length - i - 1)) + ans;
		}
		return ans;
	}

	// 取子串
	public static byte[] subbignum0(byte[] A, int begin, int length) {
		if (length == A.length && begin == 0) {
			return A;
		} else {
			byte[] TMP = new byte[length];
			for (int i = 0; i < length; i++) {
				TMP[i] = A[begin + i];
			}
			int k = 0;
			for (int i = 0; i < length; i++) {
				if (TMP[i] == 0) {
					k++;
				} else
					break;
			}
			if (k != 0) {
				TMP = subbignum0(TMP, k, length - k);
			}
			return TMP;
		}
	}

	// 纯粹取子串
	public static byte[] subbignum(byte[] A, int begin, int length) {
		byte[] TMP = new byte[length];
		for (int i = 0; i < length; i++) {
			TMP[i] = A[begin + i];
		}
		return TMP;
	}

	// bignum型乘法
	public static byte[] bignummul(byte[] x, byte[] y) {
		if (bignumtoint(x) == 0 || bignumtoint(y) == 0) {
			return inttobignum(0);
		} else {
			byte[] A;
			byte[] B;
			byte[] a, b, c, d;
			if (x.length < y.length) {
				A = y;
				B = x;
			} else {
				A = x;
				B = y;
			}
			int B_length = B.length;
			int A_length = A.length;
			byte[] z = null;
			int n = A_length;
			if (n % 2 == 1) {
				n = (n + 1) / 2;
			} else
				n = n / 2;
			if (n == 1) {
				z = inttobignum(bignumtoint(A) * bignumtoint(B));
			} else {
				if (A_length <= n) {
					a = inttobignum(0);
					b = A;
				} else {
					a = subbignum0(A, 0, A_length - n);
					b = subbignum0(A, A_length - n, n);
				}
				if (B_length <= n) {
					c = inttobignum(0);
					d = B;
				} else {
					c = subbignum0(B, 0, B_length - n);
					d = subbignum0(B, B_length - n, n);
				}
				byte[] u = bignummul(bignumadd(a, b), bignumadd(c, d));
				byte[] v = bignummul(a, c);
				byte[] w = bignummul(b, d);
				z = bignumcut0(bignumadd(bignumadd(bignumextend(v, (byte) 0, 2 * n),
						bignumextend((bignumminus(bignumminus(u, v), w)), (byte) 0, n)), w));
			}
			return z;
		}
	}

	// 单位乘法
	public static byte[] bignummul1(byte[] A, byte a) {
		byte[] zero = { 0 };
		if (a == 0 || (A.length == 1 && bignumtoint(A) == 0)) {
			return zero;
		}
		int A_length = A.length;
		byte up = 0;
		byte[] C = new byte[A_length];
		System.arraycopy(A, 0, C, 0, A_length);
		byte[] B = null;
		for (int i = A_length - 1; i >= 0; i--) {
			int tmp = C[i] * a + up;
			if (tmp >= 10) {
				C[i] = (byte) ((C[i] * a + up) % 10);
				up = (byte) (tmp / 10);
			} else {
				C[i] = (byte) (C[i] * a + up);
				up = 0;
			}
		}
		if (up != 0) {
			B = new byte[A_length + 1];
			B[0] = (byte) (up);
			for (int k = 0; k < A_length; k++) {
				B[k + 1] = C[k];
			}
			return B;
		} else
			return C;
	}

	// 改进后的乘法
	public static byte[] bignummuls(byte[] A, byte[] B) {
		byte[] zero = { 0 };
		if ((A.length == 1 && bignumtoint(A) == 0) || (B.length == 1 && bignumtoint(B) == 0)) {
			return zero;
		}
		byte[] a1 = bignummul1(A, (byte) 1);
		byte[] a2 = bignummul1(A, (byte) 2);
		byte[] a3 = bignummul1(A, (byte) 3);
		byte[] a4 = bignummul1(A, (byte) 4);
		byte[] a5 = bignummul1(A, (byte) 5);
		byte[] a6 = bignummul1(A, (byte) 6);
		byte[] a7 = bignummul1(A, (byte) 7);
		byte[] a8 = bignummul1(A, (byte) 8);
		byte[] a9 = bignummul1(A, (byte) 9);
		int B_length = B.length;
		byte[] add = { 0 };
		int k = 0;
		for (int i = B_length - 1; i >= 0; i--) {
			if (B[i] == 1) {
				add = bignumadd(bignumextend(a1, (byte) 0, k), add);
				k++;
			} else if (B[i] == 2) {
				add = bignumadd(bignumextend(a2, (byte) 0, k), add);
				k++;
			} else if (B[i] == 3) {
				add = bignumadd(bignumextend(a3, (byte) 0, k), add);
				k++;
			} else if (B[i] == 4) {
				add = bignumadd(bignumextend(a4, (byte) 0, k), add);
				k++;
			} else if (B[i] == 5) {
				add = bignumadd(bignumextend(a5, (byte) 0, k), add);
				k++;
			} else if (B[i] == 6) {
				add = bignumadd(bignumextend(a6, (byte) 0, k), add);
				k++;
			} else if (B[i] == 7) {
				add = bignumadd(bignumextend(a7, (byte) 0, k), add);
				k++;
			} else if (B[i] == 8) {
				add = bignumadd(bignumextend(a8, (byte) 0, k), add);
				k++;
			} else if (B[i] == 9) {
				add = bignumadd(bignumextend(a9, (byte) 0, k), add);
				k++;
			} else if (B[i] == 0) {
				k++;
			}
		}
		return add;
	}

	// 减去后面的0
	public static byte[] bignumcut0back(byte[] A) {
		int k = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			if (A[i] == 0) {
				k++;
			} else
				break;
		}
		byte[] ans = subbignum0(A, 0, A.length - k);
		return ans;
	}

	// 比较大小
	public static boolean bigup(byte[] A, byte[] B) {
		if (A.length > B.length) {
			return true;
		} else if (B.length > A.length) {
			return false;
		} else {
			for (int i = 0; i < A.length; i++) {
				if (A[i] > B[i]) {
					return true;
				} else if (A[i] < B[i]) {
					return false;
				}
			}
			return true;
		}
	}

	// 除2
	public static byte[] bignumdiv2(byte[] A) {
		byte lend = 0;
		for (int i = 0; i < A.length; i++) {
			int tmp = (lend * 10 + A[i]);
			if ((lend * 10 + A[i]) % 2 != 0) {
				lend = 1;
			} else
				lend = 0;
			A[i] = (byte) (tmp / 2);
		}
		if (A.length > 1) {
			A = bignumcut0(A);
		}
		return A;
	}

	// 求模逆
	public static byte[] bignummulinver(byte[] a, byte[] m) {
		byte[] u = new byte[m.length];
		byte[] v = new byte[a.length];
		System.arraycopy(m, 0, u, 0, m.length);
		System.arraycopy(a, 0, v, 0, a.length);
		byte[] B = { 0 };
		byte[] D = { 1 };
		byte[] A = { 1 };
		byte[] C = { 0 };
		int B_sig = 1;
		int D_sig = 1;
		int A_sig = 1;
		int C_sig = 1;
		while (bignumtoint(u) != 0) {
			if (u[u.length - 1] % 2 == 0) {
				u = bignumdiv2(u);
				if (B[B.length - 1] % 2 == 0 && A[A.length - 1] % 2 == 0) {
					B = bignumdiv2(B);
					A = bignumdiv2(A);
				} else {
					if (A_sig == 1) {
						A = bignumdiv2(bignumadd(A, a));
						A_sig = 1;
					} else {
						A = bignumdiv2(bignumminus(A, a));
						if (bigup(A, a)) {
							A_sig = 0;
						} else {
							A_sig = 1;
						}
					}
					if (B_sig == 1) {
						B = bignumdiv2(bignumminus(B, m));
						if (bigup(m, B)) {
							B_sig = 0;
						} else {
							B_sig = 1;
						}
					} else {
						B = bignumdiv2(bignumadd(B, m));
						B_sig = 0;
					}
				}
			} else if (v[v.length - 1] % 2 == 0) {
				v = bignumdiv2(v);
				if (D[D.length - 1] % 2 == 0 && C[C.length - 1] % 2 == 0) {
					D = bignumdiv2(D);
					C = bignumdiv2(C);
				} else {
					if (C_sig == 1) {
						C = bignumdiv2(bignumadd(C, a));
						C_sig = 1;
					} else {
						C = bignumdiv2(bignumminus(C, a));
						if (bigup(C, a)) {
							C_sig = 0;
						} else {
							C_sig = 1;
						}
					}
					if (D_sig == 1) {
						D = bignumdiv2(bignumminus(D, m));
						if (bigup(m, D)) {
							D_sig = 0;
						} else {
							D_sig = 1;
						}

					} else {
						D = bignumdiv2(bignumadd(D, m));
						D_sig = 0;
					}
				}
			} else {
				if (bigup(u, v)) {
					u = bignumminus(u, v);

					if (B_sig == 0 && D_sig == 1) {
						B = bignumadd(B, D);
						B_sig = 0;
					} else if (B_sig == 0 && D_sig == 0) {
						if (bigup(D, B)) {
							B_sig = 1;
						} else
							B_sig = 0;
						B = bignumminus(D, B);
					} else if (B_sig == 1 && D_sig == 0) {
						B = bignumadd(B, D);
						B_sig = 1;
					} else if (B_sig == 1 && D_sig == 1) {
						B = bignumminus(B, D);
						if (bigup(D, B)) {
							B_sig = 0;
						} else
							B_sig = 1;
					}
					if (A_sig == 0 && C_sig == 1) {
						A = bignumadd(A, C);
						A_sig = 0;
					} else if (A_sig == 0 && C_sig == 0) {
						A = bignumminus(C, A);
						if (bigup(C, A)) {
							A_sig = 1;
						} else
							A_sig = 0;
					} else if (A_sig == 1 && C_sig == 0) {
						A = bignumadd(A, C);
						A_sig = 1;
					} else if (A_sig == 1 && C_sig == 1) {
						if (bigup(C, A)) {
							A_sig = 0;
						} else
							A_sig = 1;
						A = bignumminus(A, C);
					}
				} else {
					v = bignumminus(v, u);
					if (D_sig == 0 && B_sig == 1) {
						D = bignumadd(D, B);
						D_sig = 0;
					} else if (D_sig == 0 && B_sig == 0) {
						if (bigup(B, D)) {
							D_sig = 1;
						} else
							D_sig = 0;
						D = bignumminus(B, D);
					} else if (D_sig == 1 && B_sig == 0) {
						D = bignumadd(D, B);
						D_sig = 1;
					} else if (D_sig == 1 && B_sig == 1) {
						if (bigup(B, D)) {
							D_sig = 0;
						} else
							D_sig = 1;
						D = bignumminus(D, B);
					}
					if (C_sig == 0 && A_sig == 1) {
						C = bignumadd(C, A);
						C_sig = 0;
					} else if (C_sig == 0 && A_sig == 0) {
						C = bignumminus(A, C);
						if (bigup(A, C)) {
							C_sig = 1;
						} else
							C_sig = 0;
					} else if (C_sig == 1 && A_sig == 0) {
						C = bignumadd(A, C);
						C_sig = 1;
					} else if (C_sig == 1 && A_sig == 1) {
						if (bigup(A, C)) {
							C_sig = 0;
						} else
							C_sig = 1;
						C = bignumminus(A, C);
					}
				}
			}
		}
		return D;
	}

	// 素数检测
	public static boolean bignumprime(byte[] n, int t) {
		if (!bignumfiltration(n)) {
			return false;
		}
		byte[] tmp = new byte[n.length];
		System.arraycopy(n, 0, tmp, 0, n.length);
		byte[] one = { 1 };
		tmp = bignumminus(tmp, one);
		int s = 0;
		while (tmp[tmp.length - 1] % 2 == 0) {
			tmp = bignumdiv2(tmp);
			s++;
		}
		byte[] n1 = bignumminus(n, one);
		byte[] r = tmp;
		// bignumprint(r);
		for (int i = 0; i < t; i++) {
			int a = (int) (Math.random() * 10 + 2);
			// System.out.println("s是" + s);
//			 System.out.println("a是" + a);
			// System.out.print("r是");
			// bignumprint(r);
			// System.out.print("n是");
			// bignumprint(n);
			byte[] y = bignummodexp_M(inttobignum(a), r, n);
			// System.out.print("y是");
			// bignumprint(y);
			if (bignumtoint(y) != 1 && !bignumequi(n1, y)) {
				int j = 1;
				while (j < s && !bignumequi(n1, y)) {
					y = bignummodmul(y, y, n);
					// System.out.print("y是");
					// bignumprint(y);
					if (y.length == 1 && bignumtoint(y) == 1) {
						return false;
					}
					j++;
				}
				if (!bignumequi(n1, y)) {
					return false;
				}
			}
		}
		return true;
	}

	// 二进制转换
	public static byte[] bignumto2(byte[] A) {
		int i = 0;
		byte[] par = new byte[A.length];
		System.arraycopy(A, 0, par, 0, A.length);
		byte[] bin = new byte[2048];
		while (bignumtoint(par) != 0) {
			bin[i] = (byte) (par[par.length - 1] % 2);
			par = bignumdiv2(par);
			i++;
		}
		bin = subbignum(bin, 0, i);
		for (int k = 0; k < i / 2; k++) {
			byte tmp = bin[k];
			bin[k] = bin[i - 1 - k];
			bin[i - 1 - k] = tmp;
		}
		return bin;
	}

	// 模指数
	public static byte[] bignummodexp(byte[] a, byte[] k, byte[] n) {
		// k = bignumto2(k);
		// byte[] b={1};
		// while(k.length!=0){
		// if(k[k.length-1]%2==0){
		// a=bignummodmul(a,a,n);
		// k=subbignum(k,0,k.length-1);
		// }else {
		// b=bignummodmul(b,a,n);
		// k[k.length-1]--;
		// }
		// if(k.length==1&&bignumtoint(k)==0){
		// break;
		// }
		// }
		// return b;
		byte[] k1 = bignumto2(k);
		byte[] b = { 1 };
		if (k1.length == 1 && bignumtoint(k1) == 0) {
			return b;
		}
		byte[] A = new byte[a.length];
		System.arraycopy(a, 0, A, 0, a.length);
		if (k1[k1.length - 1] == 1) {
			b = a;
		}
		for (int i = k1.length - 2; i >= 0; i--) {
			A = bignummodmul(A, A, n);
			if (k1[i] == 1) {
				b = bignummodmul(A, b, n);
			}
		}
		return b;
	}

	// 模乘
	public static byte[] bignummodmul(byte[] A, byte[] B, byte[] N) {
		byte[] zero = { 0 };
		while (A.length > N.length) {
			if (bigup(A, bignumextend(N, (byte) 0, A.length - N.length))) {
				A = bignumminus(A, bignumextend(N, (byte) 0, A.length - N.length));
			} else
				A = bignumminus(A, bignumextend(N, (byte) 0, A.length - N.length - 1));
		}
		while (bigup(A, N)) {
			A = bignumminus(A, N);
		}
		while (B.length > N.length) {
			if (bigup(B, bignumextend(N, (byte) 0, B.length - N.length))) {
				B = bignumminus(B, bignumextend(N, (byte) 0, B.length - N.length));
			} else
				B = bignumminus(B, bignumextend(N, (byte) 0, B.length - N.length - 1));
		}
		while (bigup(B, N)) {
			B = bignumminus(B, N);
		}
		byte[] C = bignummuls(A, B);
		if (C.length == 1 && bignumtoint(C) == 0) {
			return zero;
		}
		if (bigup(N, C)) {
			return C;
		} else {
			while (C.length > N.length) {
				if (bigup(C, bignumextend(N, (byte) 0, C.length - N.length))) {
					C = bignumminus(C, bignumextend(N, (byte) 0, C.length - N.length));

				} else

					C = bignumminus(C, bignumextend(N, (byte) 0, C.length - N.length - 1));
			}
			while (bigup(C, N)) {
				C = bignumminus(C, N);
			}
		}
		return C;
	}

	// 二进制减法
	public static byte[] bignumminus2(byte[] A, byte[] B) {
		if (Arrays.equals(A, B)) {
			return inttobignum(0);
		} else {
			if (bigup(B, A)) {
				byte[] tmp = new byte[A.length];
				System.arraycopy(tmp, 0, A, 0, A.length);
				A = B;
				B = tmp;
			}
			byte[] tmp = new byte[A.length];
			for (int i = 0; i < B.length; i++) {
				tmp[A.length - i - 1] = B[B.length - i - 1];
			}
			byte lend = 0;
			for (int i = 0; i < A.length; i++) {
				int tm;
				tm = A[A.length - 1 - i] - tmp[tmp.length - i - 1] - lend;
				A[A.length - 1 - i] = (byte) (Math.abs((A[A.length - 1 - i] - tmp[tmp.length - i - 1] - lend) % 2));
				if (tm < 0) {
					lend = 1;
				} else
					lend = 0;
				A = bignumcut0(A);
			}
			return A;
		}
	}

	// 二进制转10进制
	public static byte[] bintobignum(byte[] A) {
		byte[] TMP = { 0 };
		if (A[A.length - 1] == 1) {
			TMP = inttobignum(1);
		}
		for (int i = 1; i < A.length; i++) {
			if (A[A.length - i - 1] == 1) {
				byte[] tmp = { 1 };
				for (int k = 0; k < i; k++) {
					tmp = bignummul1(tmp, (byte) 2);
				}
				TMP = bignumadd(TMP, tmp);
			}
		}
		return TMP;
	}

	// 模指数运算
	public static byte[] bignummodexps(byte[] M, byte[] e, byte[] n) {
		e = bignumto2(e);
		int d = 4;
		long startTime = System.currentTimeMillis();
		String[] TMP = new String[(int) Math.pow(2, d)];
		TMP[1] = bignumtostr(M);
		for (int u = 3; u < Math.pow(2, d); u = u + 2) {
			TMP[u] = bignumtostr(bignummodexp(M, inttobignum(u), n));
		}
		long endTime = System.currentTimeMillis();
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
		int[] F = new int[e.length];
		int[] zlen = new int[e.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < e.length) {
			if (e[i] == 1) {
				int tmp = i;
				if (i + d - 1 < e.length) {
					while (e[i + d - 1] != 1) {
						i--;
					}
					F[j] = bignumtoint(subbignum(e, tmp, d - (tmp - i)));
					i = i + d;
					// System.out.print(F[j] + " ");
					j++;
				} else {
					int u = e.length - 1;
					while (e[u] == 0) {
						u--;
					}
					F[j] = bignumtoint(subbignum(e, i, u - i + 1));
					// System.out.print(F[j] + " ");
					i = u + 1;
					j++;
				}
			} else {
				int cont = 0;
				while (i < e.length && e[i] == 0) {
					i++;
					cont++;
				}
				F[j] = 0;
				zlen[k] = cont;
				// System.out.print(F[j] + " ");
				k++;
				j++;
			}
		}
		int[] nw = new int[j];
		System.arraycopy(F, 0, nw, 0, j);
		int[] win = new int[j];
		int o = 0;
		for (int p = 0; p < j; p++) {
			if (nw[p] != 0) {
				win[p] = String.valueOf(nw[p]).length();
			} else {
				win[p] = zlen[o];
				o++;
			}
		}
		// System.out.println("");
		// System.out.println("窗口长度");
		// for (int tmp = 0; tmp < j; tmp++) {
		// System.out.print(win[tmp] + " ");
		// }
		// System.out.println("");
		byte[] C = bignumexp2(M, inttobignum(nw[0]));
		byte[] tmp = { 2 };
		for (int q = 1; q < j; q++) {
			if (win[q] != 0) {
				C = bignummodexp(C, bignumexp2(tmp, bignumto2(inttobignum(win[q]))), n);
				// bignumprint(bignumexp2(tmp, bignumto2(inttobignum(win[q]))));
			}
			if (nw[q] != 0) {
				// System.out.print(bignumtoint(bintobignum(inttobignum(F[q])))+"
				// ");
				C = bignummodmul(C, setBignum(TMP[bignumtoint(bintobignum(inttobignum(F[q])))]), n);
			}
		}
		return C;
	}

	// 指数运算
	public static byte[] bignumexp2(byte[] g, byte[] e) {
		// int k = 3;
		// String[] G = new String[8];// 2^(k-1)-1
		// G[1] = bignumtostr(g);
		// G[2] = bignumtostr(bignummuls(g, g));
		// for (int i = 1; i < 4; i++) {
		// G[2 * i + 1] = bignumtostr(bignummuls(setBignum(G[2 * i - 1]),
		// setBignum(G[2])));
		// }
		// byte[] A = { 1 };
		// int i = 0;
		// while (i <e.length) {
		// if (e[i] == 0) {
		// A = bignummuls(A, A);
		// i++;
		// } else {
		// int l=i+k-1;
		// if(l>=e.length-1){
		// l=e.length-1;
		// }
		// while(e[l]==0){
		// l--;
		// }
		// int tmp=1;
		// for(int q=0;q<l-i+1;q++){
		// tmp=tmp*2;
		// }
		// byte[] TMP={1};
		// for(int p=0;p<tmp;p++){
		// TMP=bignummuls(A,TMP);
		// }
		// A=bignummuls(TMP,setBignum(G[bignumtoint(bintobignum(subbignum(e,i,l-i+1)))]));
		// i=l+1;
		// }
		// }
		// return A;
		byte[] g0 = { 1 };
		byte[] g1 = g;
		byte[] A = { 1 };
		for (int i = 0; i < e.length; i++) {
			A = bignummuls(A, A);
			if (e[i] == 1) {
				A = bignummuls(A, g1);
			} else
				A = bignummuls(A, g0);
		}
		return A;
	}

	// 求模
	public static byte[] bignummod(byte[] a, byte[] m) {
		byte[] zero = { 0 };
		if (a.equals(m)) {
			return zero;
		}
		if (bigup(m, a)) {
			return a;
		}
		while (a.length > m.length) {
			if (bigup(a, bignumextend(m, (byte) 0, a.length - m.length))) {
				a = bignumminus(a, bignumextend(m, (byte) 0, a.length - m.length));
			} else
				a = bignumminus(a, bignumextend(m, (byte) 0, a.length - m.length - 1));
		}
		while (bigup(a, m)) {
			a = bignumminus(a, m);
		}
		return a;
	}

	// 过滤
	public static boolean bignumfiltration(byte[] a) {
		if (a[a.length - 1] == 5 || a[a.length - 1] == 0) {
			return false;
		}
		int add = 0;
		for (int i = 0; i < a.length; i++) {
			add = a[i] + add;
		}
		if (add % 3 == 0 && add != 3) {
			return false;
		}
		int addodd = 0;
		int addeven = 0;
		for (int i = 0; i < a.length; i = i + 2) {
			addeven = addeven + a[i];
		}
		for (int i = 1; i < a.length; i = i + 2) {
			addodd = addodd + a[i];
		}
		if ((addeven - addodd) % 11 == 0) {
			return false;
		}
		int[] prime = { 7, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103,
				107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223,
				227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347,
				349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
				467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607,
				613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743,
				751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883,
				887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997 };
		for (int i = 0; i < prime.length; i++) {
			if (bignumtoint(bignummod(a, inttobignum(prime[i]))) == 0) {
				return false;
			}
		}
		return true;
	}

	// montgomery乘法
	public static byte[] bignummodmul_M(byte[] x, byte[] y, byte[] m) {
		if(bigup(x,m)){
			x=bignummod(x,m);
		}
		if(bigup(y,m)){
			y=bignummod(y,m);
		}
		byte[] zero={0};
		int m_length = m.length;
		int m_1 = 0;
		if (m[m_length - 1] == 1) {
			m_1 = 9;
		}
		if (m[m_length - 1] == 3) {
			m_1 = 3;
		}
		if (m[m_length - 1] == 7) {
			m_1 = 7;
		}
		if (m[m_length - 1] == 9) {
			m_1 = 1;
		}
		byte[] A = { 0 };
		for (int i = 0; i < m.length; i++) {
			if (i > x.length - 1) {
				byte u = (byte) ((A[A.length - 1] * m_1) % 10);
				byte[] tmp = bignumadd(A, bignummul1(m, u));
				A = subbignum(tmp, 0, tmp.length - 1);
			} else {
				byte u = (byte) (((A[A.length - 1] + x[x.length - 1 - i] * y[y.length - 1]) * m_1) % 10);
//				System.out.println("u*m是");
//				bignumprint(bignummul1(m, u));
//				System.out.println("u是");
//				System.out.println(u);
				byte[] tmp = bignumadd(bignumadd(A, bignummul1(y, x[x.length - 1 - i])), bignummul1(m, u));
//				System.out.println("tmp");
//				bignumprint(tmp);
				if(tmp.length==1&&bignumtoint(tmp)==0){
					A=zero;
				}else{
				A = subbignum(tmp, 0, tmp.length - 1);
//				System.out.println("A是");
//				bignumprint(A);
				}
			}
		}
		if (bigup(A, m)) {
			A = bignumminus(A, m);
		}
		return A;
	}

	// Montgomery模幂
	public static byte[] bignummodexp_M(byte[] x, byte[] e, byte[] m) {
		e = bignumto2(e);
		byte[] one = { 1 };
		byte[] R = bignumextend(one, (byte) 0, m.length);
		byte[] x_1 = bignummodmul_M(x, bignummod(bignumextend(one, (byte) 0, 2 * m.length), m), m);
//		bignumprint(x_1);
		byte[] A = R;
		for (int i = 0; i < e.length; i++) {
//			bignumprint(A);
			A = bignummodmul_M(A, A, m);
//			bignumprint(A);
			if (e[i] == 1) {
				A = bignummodmul_M(A, x_1, m);
			}
		}
		A=bignummodmul_M(A,one,m);
		return A;
	}

	// 相等
	public static boolean bignumequi(byte[] a, byte[] b) {
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static byte[] makeprime(int t) {
		byte[] ans=bignumrd(t);
		byte[] two={2};
		while(1!=0){
			if(bignumprime(ans,3)){
				break;
			}else ans=bignumadd(ans,two);
		}
		return ans;
	}
	public static byte[] lettertobignum(String val) {
		val = val.toUpperCase();
		byte a[] = val.getBytes();
		byte[] b = new byte[a.length * 2];
		for (int i = 0; i < a.length; i++) {
			b[i * 2] = (byte) (a[i] / 10);
			b[i * 2 + 1] = (byte) (a[i] % 10);
		}
		return b;
	}
	public static String bignumtoletter(byte[] val) {
		byte[] a = new byte[val.length / 2];
		for (int i = 0; i < a.length; i++) {
			a[i] = (byte) (val[i * 2] * 10 + val[i * 2 + 1]);
		}
		String t = new String(a);
		t = t.toLowerCase();
		return t;
	}
}
