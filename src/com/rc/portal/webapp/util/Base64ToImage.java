package com.rc.portal.webapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64ToImage 
{
    public static void main(String[] args)
    {
        String strImg = GetImageStr();
        System.out.println(strImg);
        //GenerateImage("data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCACAAIADAREAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD/AD/6ACgAoAKACgAoAKAP6nf+DXT/AII+/swf8FRfjL+0r46/a4HiDxl8Kf2WvD/wwtovgbo2reIPBunfE/xV8dIPivY6PrPiz4g+DvEWgeNtH8P/AA6tvhnqeqW3hnwjeaLqPinxVq/hm+1PxZaeFvC/iHwV8QQA/wCDov8A4I+/swf8EuvjL+zV46/ZHHiDwb8Kf2pfD/xPtpfgbrOreIPGWnfDDxV8C4PhRY6xrPhP4g+MfEWv+NtY8P8AxFtviZpmqXPhnxdea1qPhbxVpHia+0zxZd+FvFHh7wV8PgD+WKgAoAKACgAoAKACgAoAKACgD279mb4N/wDDRf7SH7Pv7Pn/AAkf/CHf8L2+N/wo+Df/AAl39j/8JD/wi3/Cz/HmgeCf+Ej/ALA/tTQ/7c/sP+3P7T/sf+2tI/tP7L9i/tTT/P8AtcIB/fH+2V/wbAfsPat+x54s8PfsfeAfG/g/9qz4f+CI9d+Hfj7Ufid4i8V6r8c/GfgvwxeKvgP4j6H4+8baL8IdA/4XPqEa2994o8HaT8LdK8B+M73RvE1hDbeAdG1z4d+IgD/PK8TeGfEngrxJ4h8G+MvD2ueEvF/hLXNW8M+KvCnibSb/AEHxJ4Z8SaDf3Gla54e8Q6HqtvaapouuaLqlpdabq2k6la21/p1/bXFneW8NxDJGoBh0Af3+f8GMf/OUX/uyb/37igA/4PnP+cXX/d7P/vo9AH8AdAGnomiaz4l1nSPDnhzSNT1/xDr+p2GiaFoWiWF1qus63rOq3UVjpekaRpdjFPfalqepX08FnYWFnBNdXl1NFb28Uk0iIwB/XV+zn/wRE/ZjsP2eNB0j9oXwp4m8Q/HfxZ4ZfVPF/iuz8bavoN/8L/EfiTRLcHwr4O0zwp4l1L4far/wri7YxWuueIrDxzYeKfEdtqOt3Uc3hTUdM8IaQAfynfGr4df8Kf8AjJ8WvhJ/bH/CRf8ACrfib48+HX/CQf2f/ZH9u/8ACEeKdV8M/wBsf2V9u1P+zP7T/sz7b/Z/9pah9i8/7N9uu/K+0SAHmdABQAUAFABQAUAFAH+pd/wQI/4KDf8ADev7BPgr/hNvE39uftC/s6f2f8E/jf8A2prX9p+KvEf9h6dH/wAK1+Lmsf2v408Y+N9X/wCFn+CILT/hI/Hvi7+w/wDhNfjN4W+Mn9gaTDpGhxYAP5n/APg6S/4Jo6V8AfjNof7fvwqtvsnw8/ai8b3Phf4yeGbTS/Bmg6D4G+PcHhWLVtL13QLfR7jSdc1n/he+h+GfHPjfxdNceGNXubD4keFfG3izxV48vLv4p+HPD2jgH8mNAH9/n/BjH/zlF/7sm/8AfuKAD/g+c/5xdf8Ad7P/AL6PQB/AHQB++P8AwQ4/YusPiv8AEfVP2r/HcP2jwj8D/E0Oh/DrRLix8OarpXif4qy6FJf32qarFqE1/qenf8Ks0zWvDHibw/JDomnzXXjLXfDOvaF4qtrjwLrGkaiAftL/AMFXf2tf+GVf2VPEv/CM61/Znxc+MH2v4afDL7DqX2LXdH/tOzf/AITP4gaf/Z/iTw74m0//AIQjwzLcf2P4r8P/ANp/8I38R9d+HX9q2Emn6m+QD+GmgAoAKACgAoA9d+FX7Pvx6+O39vf8KQ+CPxe+Mn/CLf2X/wAJP/wqr4a+M/iH/wAI5/bn9o/2L/b3/CI6Lq/9kf2v/ZGrf2X/AGh9n/tD+y9R+yed9iufKAPIqACgD9S/+CNn7eX/AA7v/b3+E/xs1+/+xfCHxX9o+Df7Qf8Aov2jy/gz8QNS0f8AtrxH/oXg/wAbeJ3/AOFaeJ9H8I/Fz+x/BOk23irxl/wr/wD4QK01Sy0/xXqfmgH+nv8AtHfAL4Cf8FCf2UfGXwS8ear/AMJx8Bf2hvBHh7UbHxf8MvF9sv8AaGlS3uh+P/hx8R/h94x0Z9U0PVP7L1zS/C/jnwpfTQeI/BniH7DYRa7o3ijwnqOo6PqQB/kV/tKfs+fEj9lL49/Fr9nH4u6Z/ZfxD+DvjfW/BPiHybLXrLStZ/su5P8AZPi7wv8A8JNovhzXNR8EeONDl03xl4D1++0PS/8AhJfBmu6F4htrSOz1S3yAf2Xf8GRvx++DXgb4y/tzfs+eMfiF4f8ADXxk+P3h/wDZ68TfBfwLrM09jqPxL074JwftC33xRtvCd5PbppGqeIPCekePfD3iK58JxaiPFWoeFY/E3ivR9G1Hw34H8bap4eAD/g9y+P3wa8c/GX9hn9nzwd8QvD/iX4yfAHw/+0L4m+NHgXRpp77Ufhpp3xsg/Z6vvhdbeLLyC3fSNL8QeLNI8BeIfEVt4Tl1E+KtP8KyeGfFesaNp3hvxx4J1TxCAfxX/Bn4SeMvjx8VfAPwd+H9l9u8XfETxNpnhnSPMttVubDTvt0w+3+INc/sTTdY1Oz8M+GNMS98ReKtVttLvv7F8OaXqmrzW729jLgA/wBAn4O/Cj4VfskfAbw78M/Ct/8A8Ix8KvhF4Z1e8uvEHjXxDCfslglzqnizxj4x8W+ItRax0yx+3anfa54o166ji0fw5pH2q7j0vTtD0Gzs9OsgD+I//gov+1P/AMNeftV+PfiXpV19p+H2g+V8OvhJ+48nf8OPCd5qP9m6x/pPh7w1ra/8Jpreo+IPiB/Z/iawm13w5/wln/CKXF9c2mg2WwA+GqACgD0z4i/BX4yfB/8Asf8A4W38Jfib8Lf+Ei/tD/hH/wDhYvgPxT4I/t3+yPsP9q/2P/wk2laZ/af9mf2npv8AaH2Lz/sX9oWP2nyvtdv5gB5nQAUAf3T/APBvF+1X+y1qX7IHgr9lzQ/EvhDwT+0l4X8X/EK+8aeBtZTw94T8V/GHUvEmueLfGmkePPBirdJffFf+xfhfpOm+FvEV5ALvxX4N034dRWfiDStK8FQeBtX10A+u/wBt7/gir+xd+2h/wkHi7/hEv+FE/HHV/wC1tR/4W98I7Kw0j+3vEmof8JZqf9qfE7wBsh8IfEP+1PF/in/hJ/GmvfZvDnxW8W/2Rpui/wDC1dI0yLywAfxa/tm/8Eqv20f2GftmtfFz4a/8JJ8L7T7P/wAXv+FU9/42+FMfn/8ACLWn/E+1b+y9L8Q/D/f4h8Xab4R0v/hZ3hjwT/wlfiSDUbbwX/wkljaf2hIAfnPQB/YL/wAEiv8Ag5Z+G/7IP7KPh39l/wDbK+HPxv8AiH/wpvZoHwV+I/wm/sHx1r2qfDe7vdW1G28E/EKx+KnxQ8Hf2R/wrDz7Pwt8Orvwnql5oL/DdfDvgqPwj4S/4V5FrPjkA/m7/b7/AGwfEn7fH7Xvxr/a18V+DtD+H2qfFzXNDuLXwV4ev7/VrDwz4b8G+DvDnw88G6Tca1qSw3Gva5beEPCWhjxN4hj0/QrDX/Eh1bWdK8M+FtLvrPw5pYB8fUAFAH0N+yj+0LrP7Kf7Qfw1+PuheHdM8W33w/1PVJZ/DWr3V1YWutaN4i8O6x4R8R2EWpWYkm0rU5vD2v6oNE1d7TVLXStZFhqN9omu2NtcaPfAH7I/8FAv+C0Hg39oT4Dav8EP2c/B/wATfCP/AAsXdpXxJ8Y+Pv7K8L6rY+Dbe5sLyfwz4RtfAvjfxF/aH/Cb+VcaH4wuNevrfSl8GtrHhp/D+v8A/CXPqPhgA/nooA+5f2WP+CdH7Vf7Xn2XVfhp4C/sH4fXPn/8Xb+Ist54T+HD+T/wkNt/xJ9S/s7Udb8abdb8NX/hnUP+Ff8Ah/xZ/wAI5rs1jb+K/wCwbS5+2oAf1Y/slf8ABKL9lT9lX+xfE3/CNf8AC4Pi5pn9m33/AAs34l2lnqf9j67Zf8I3qH9ofD/wZsl8M+CP7P8AE3h3/hIPCmsfZ9d+I/hv+07/AEr/AIWLqenybCAfM3/Bbv8AaM/Z4sP2Y/Ff7PWr694Z8WfHfxD4m8E3nhTwhpaaJ4k8R/C+/wBB1fw14r1Pxj4qBuGu/hx/avw+1K/8O6HdSi28R+KbDxzJa6Jp2o+FJvF+p6QAfyBUAFAGv4f8Qa/4S1/Q/FXhXXNX8M+KPDOr6b4g8N+JPD+pXui6/wCH9f0W9h1LR9c0PWNNnttR0nV9J1G2t7/TdSsLi3vbC9t4bq1ninijkUA/pJ/YA/4OLfit8FNNg+G/7a2j+L/2ifA9t/Z1t4f+Kvh2fw3D8aPB+jaN4Un0uDRdZ07Uo/D+kfGX+1dX03w9PL4n8XeMPDfj2zn1Txn4j8S+LviPdXeh6DpYB/XZ8Ffjx+zX+3B8F9S8Z/CPxV4Q+OXwY8X/APCUfD7xPZ32h3cmm3u2B9K8VeCPHngPxto+navp39o6RqMMt54a8Y+HbP8AtzwprulazHZX3hrxFpV9fgH8oP8AwX9/4Jifs1/sveAvAn7Vf7O+hf8ACqv+E4+L3/Cs/Hnwl0VLu68BajrPjHRviH8R7Pxx4Rt77U5v+Fef2X/wi2o+GrnwJ4ctV8BNo114YTwloXgf/hGdVj8YAH8uNABQAUAFABQAUAf0L/8ABF//AIJ+/Ab9oTwb4w/aM+N+kf8ACxf+ER+Jv/CA+Dvhtqq3Nv4NsdV8L6V4I8dXXi7xNBZ38X/Cb/2h/wAJFY6Db+D9ct28GrpVv4gTxLo/i7+39OTwwAf0ffFf4xfAb9kj4VWHir4meIvDPwi+FXhj/hHvBXh+1s9IuUsLTMK6d4d8JeDvB3hPS77U777DpljLJa6D4X0O6/sjw5o+o6pJaWeg6HqN5ZAH8zf7aP8AwXH+I/xXsJvAn7KGl+Jvgf4RuPt1vrfxF1ybQpfir4n0rVfDkOny6VpdjYR61pnws/s7U7/W5o/EHhnxPrvjK6m0/wAK67oPibwLcW2r6PqAB+EOt63rPiXWdX8R+I9X1PX/ABDr+p3+t67rut391qus63rOq3Ut9qmr6vql9LPfalqepX0895f395PNdXl1NLcXEsk0juwBmUAFABQAUAeu/BD4+/Gj9mzx7p/xO+A/xM8X/Czxxp32SL+3PCOrz6d/aum2ms6T4h/4R3xPpuZNI8X+EL7V9C0e71rwZ4q0/WfCniD+zra31zRtRtU8ggHo/wC1H+2p+1B+2lr/AIW8SftM/FvV/idqPgjSLzQ/CVvPo/hbwroHh+y1O9F/q0+m+FfA2heGfC9vq+tTxWMeueIBo51/WrLR/D+m6rqV5p3hzQbXTgD5boAKACgAoAKACgD6G/Z6/au/aD/ZU1nxFrvwC+JWp/D++8W6Za6R4lgi0vw74i0bWrWwujeabLf+HPF2j6/4em1PSppLtNI1s6WNZ0q11TW7HTr+2sdd1i3vgDhfi38Zvir8ePGV78QPjF4+8TfETxdffaY/7X8TanNff2dYXOq6lrf9h+H7DKaZ4Y8M2ep6xqlzpXhXw5ZaX4c0X7dcQ6Rpdjbv5QAPM6ACgAoA/ZL9hj/gjz48/bD+D4+N3iP4taZ8HPBevane2Hw6SLwhH8RtZ8YWug6nquheJ9bv7G28aeEYfCumWHiHTJ9D0iK8u77WdYutP1u8uNI0fRoPD+qeJwD7S/4h1P8Aq8P/AM19/wDx3UAH/EOp/wBXh/8Amvv/AOO6gDhviZ/wb3ePPDvgPxRrvwx/aK0z4l+PNJ0yS/8ADngDV/hfH8PrXxbdW0kUk+iReMbj4oeJbHRNTvLFbpNEl1TS00a51n7BY61q/h3Srq88Q6WAfzqUAFABQAUAFABQAUAFAH9Ffwz/AODe7x54i8B+F9d+J37RWmfDTx5q2mR3/iPwBpHwvj+INr4SurmSWSDRJfGNv8UPDVjrep2di1qmty6Xpb6Nbaz9vsdF1fxFpVrZ+IdUAO5/4h1P+rw//Nff/wAd1AB/xDqf9Xh/+a+//juoA+Lf25/+CPPjz9jz4Pn43eHPi1pnxj8F6DqdlYfEVJfCEfw51nwfa69qelaF4Y1uwsbnxp4uh8VaZf8AiHU4ND1eKzu7HWdHutQ0S8t9I1jRp/EGqeGAA/YY/wCCw3jz9jz4Pj4I+I/hLpnxj8F6Dqd7f/Dp4vF8fw51nwfa69qeq674n0S/vrbwX4uh8VaZf+IdTn1zSJby0sdZ0e61DW7O41fWNGn8P6X4YAPtL/iIr/6s8/8ANgv/AMSNAB/xEV/9Wef+bBf/AIkaAOG+Jn/Bwj488ReA/FGhfDH9nXTPhp481bTJLDw54/1f4oR/EG18JXVzJFHPrcXg64+F/hqx1vU7Oxa6fRItU1R9GttZ+wX2taR4i0q1vPD2qAH86lABQAUAFABQAUAFABQB/RX8M/8Ag4R8eeHfAfhfQvid+zrpnxL8eaTpkdh4j8f6R8UI/h9a+Lbq2kljg1uXwdb/AAv8S2OianeWK2r63FpeqJo1zrP2++0XSPDulXVn4e0sA7n/AIiK/wDqzz/zYL/8SNAB/wARFf8A1Z5/5sF/+JGgD4t/bn/4LDePP2w/g+fgj4c+EumfBzwXr2p2V/8AEV5fF8fxG1nxha6Dqela74Y0SwvrnwX4Rh8K6ZYeIdMg1zV5bO0vtZ1i60/RLO31fR9Gg8QaX4nAPxtoAKACgD0z4SfBn4q/HjxlZfD/AODvgHxN8RPF199mk/sjwzpk19/Z1hc6rpuif254gv8ACaZ4Y8M2ep6xpdtqvirxHe6X4c0X7dbzavqljbv5oAO6/aF/ZR/aD/ZU1nw7oXx9+Gup/D++8W6Zdav4anl1Tw74i0bWrWwuhZ6lFYeI/COsa/4em1PSppLR9X0QaoNZ0q11TRL7UbC2sdd0e4vgD55oAKACgAoAKACgD6G/Z6/ZR/aD/ar1nxFoXwC+Gup/EC+8JaZa6v4lni1Tw74d0bRbW/ujZ6bFf+I/F2saB4eh1PVZo7t9I0Q6odZ1W10vW77TrC5sdC1i4sQDhfi38Gfir8B/GV78P/jF4B8TfDvxdY/aZP7I8TaZNY/2jYW2q6lon9ueH7/D6Z4n8M3mp6PqltpXirw5e6p4c1r7DcTaRql9bp5pAPM6ACgAoAKANPRNE1nxLrOkeHPDmkanr/iHX9TsNE0LQtEsLrVdZ1vWdVuorHS9I0jS7GKe+1LU9Svp4LOwsLOCa6vLqaK3t4pJpERgD93v2Lv+CHHxH+K9hD47/av1TxN8D/CNx9huNE+HWhw6FL8VfE+lar4cm1CLVdUvr+TWtM+Fn9nanf6JDJ4f8TeGNd8ZXU2n+KtC17wz4FuLbSNY1AA/pk+FHwd+A37JHwqv/Cvwz8O+GfhF8KvDH/CQ+NfEF1eavcpYWmYW1HxF4t8Y+MfFmqX2p332HTLGKO617xRrl1/ZHhzR9O0uO7s9B0PTrOyAP5wf+C0H/BQL4DftCeDfB/7OfwQ1f/hYv/CI/E3/AIT7xj8SdKa5t/BtjqvhfSvG/gW18I+GZ7ywi/4Tf+0P+EivteuPGGh3DeDV0q38Pv4a1jxd/b+ov4YAP56KACgAoAKACgAoA/oX/wCCL/8AwUC+A37Pfg3xh+zn8b9X/wCFdf8ACXfE3/hPvB3xJ1Vrm48G32q+KNK8EeBbrwj4mns7CX/hCP7P/wCEdsdet/GGuXC+DW0q48QP4l1jwj/YGnP4nAP6Pviv8HfgN+1v8KrDwr8TPDvhn4u/CrxP/wAI9418P3Vnq9y9hd4hXUfDvi3wd4x8J6pY6nY/btMvpY7XXvC+uWv9r+HNY1HS5Lu80HXNRs70A/mb/bR/4IcfEf4UWE3jv9lDVPE3xw8I2/2641v4da5DoUXxV8MaVpXhyHUJdV0u+sJNF0z4p/2jqdhrcMfh/wAM+GNC8ZWs2oeFdC0Hwz46uLnV9Y08A/CHW9E1nw1rOr+HPEekanoHiHQNTv8ARNd0LW7C60rWdE1nSrqWx1TSNX0u+igvtN1PTb6Cezv7C8ghurO6hlt7iKOaN0UAzKACgD+v3/giJ+zn+zxYfsx+FP2hdI0Hwz4s+O/iHxN42s/Ffi/VH0TxJ4j+F9/oOr+JfCmmeDvCpFu138OP7V+H2pWHiLXLWI23iPxTYeOY7rW9R1HwpN4Q0zSAD6Z/a1/4Ku/sqfsq/wBteGf+El/4XB8XNM/tKx/4Vl8NLuz1P+x9dsv+Ek0/+z/iB4z3y+GfBH9n+JvDv/CP+K9H+0a78R/Df9p2Gq/8K61PT5N5AP5Tv2p/+Ci/7Vf7Xn2rSviX49/sH4fXPkf8Wk+HUV54T+HD+T/wj1z/AMTjTf7R1HW/Gm3W/DVh4m0//hYHiDxZ/wAI5rs19ceFP7BtLn7EgB8NUAf0L/8ABP3/AIIv+Df2hPgNpHxv/aM8YfE3wj/wsXbqvw28HeAf7K8L6rY+Dbe5v7ODxN4uuvHXgjxF/aH/AAm/lW+ueD7fQbG30pfBraP4lTxBr/8Awlyad4YAPxu/au/Z61n9lP8AaD+JXwC13xFpni2++H+p6XFB4l0i1urC11rRvEXh3R/F3hy/l028Mk2lanN4e1/SzrekJd6pa6VrIv8ATrHW9dsba31i+APnmgAoA+hv2Uf2etZ/as/aD+GvwC0LxFpnhK++IGp6pFP4l1e1ur+10XRvDvh3WPF3iO/i02zMc2q6nD4e0DVDomkPd6Xa6rrJsNOvtb0KxubjWLEA/ZH/AIKBf8EX/Bv7PfwG1f43/s5+MPib4u/4V1u1X4k+DvH39leKNVvvBtxc2FnP4m8I3XgXwR4d/s//AIQjzbjXPGFvr1jcaU3g1dY8Sv4g0D/hEX07xOAfz0UAfcv7LH/BRf8Aar/ZD+y6V8NPHv8Ab3w+tvP/AOLSfEWK88WfDhPO/wCEhuf+JPpv9o6drfgvdrfiW/8AE2of8K/8QeE/+Ej12GxuPFf9vWlt9icA/qx/ZK/4Ku/sqftVf2L4Z/4SX/hT/wAXNT/s2x/4Vl8S7uz0z+2Ndvf+Eb0/+z/h/wCM98Xhnxv/AGh4m8Rf8I/4U0f7RoXxH8Sf2Zf6r/wrrTNPj3gA+Zv+C3f7Of7PF/8Asx+K/wBoXV9B8M+E/jv4e8TeCbPwp4v0t9E8N+I/ihf69q/hrwpqfg7xUTbrd/Ef+yvh9pt/4i0O1lNz4j8LWHgaS60TUdO8KQ+L9M1cA/kCoAKAPTPh18avjJ8H/wC2P+FSfFr4m/C3/hIv7P8A+Eg/4V1488U+CP7d/sj7d/ZX9sf8Izqumf2n/Zn9p6l/Z/23z/sX9oX32byvtdx5gB5nQAUAfcv/AATo/ZY/4a8/ar8BfDTVbX7T8PtB834i/Fv9/wCTv+HHhO807+0tH/0bxD4a1tf+E01vUfD/AMP/AO0PDN/Nrvhz/hLP+Ert7G5tNBvdgB/bh8Yviv8ACr9kj4DeI/iZ4qsP+EY+FXwi8M6RZ2vh/wAFeHoT9ksEudL8J+DvB3hLw7py2OmWP27U77Q/C+g2skuj+HNI+1Wkmqajoeg2d5qNkAf5+3xm+LfjL48fFXx98YviBe/bvF3xE8Tan4m1fy7nVbmw077dMfsHh/Q/7b1LWNTs/DPhjTEsvDvhXSrnVL7+xfDml6XpENw9vYxYAP3f/wCDe74Z+A/EXjz9or4na74X0zVvHnw00z4X6R4B8R38clzdeErX4gx/FC38Yy6JBJK1jZ6nrdj4a0vS5dbS1/tm20Z9X0Wxv7XSvEXiGz1QAP8Ag4R+GfgPw748/Z1+J2heF9M0nx58S9M+KGkePvEdhHJbXXi21+H0fwvt/B0utwRyrY3mp6JY+JdU0uLW3tf7ZudGTSNFvr+60rw74es9LAPwg+DPxb8ZfAf4q+AfjF8P737D4u+HfibTPE2keZc6rbWGo/YZh9v8P65/YmpaPqd54Z8T6Y974d8VaVbapY/214c1TVNImuEt76XIB/oE/B34r/Cr9rf4DeHfiZ4VsP8AhJ/hV8XfDOr2d14f8a+HoR9rsHudU8J+MfB3i3w7qK32mX32HU7HXPC+vWscuseHNX+y3cml6jrmg3lnqN6AfxH/APBRf9lj/hkP9qvx78NNKtfs3w+17yviL8JP3/nbPhx4svNR/s3R/wDSfEPiXW2/4QvW9O8QfD/+0PE1/DrviP8A4RP/AISu4sba016y3gHw1QAUAemfEX41fGT4wf2P/wALb+LXxN+KX/CO/wBof8I//wALF8eeKfG/9hf2v9h/tX+x/wDhJtV1P+zP7T/szTf7Q+xeR9t/s+x+0+b9kt/LAPM6ACgAoAKACgD+5b/glF+yV/wyr+yp4a/4SbRf7M+Lnxg+yfEv4m/btN+xa7o/9p2af8IZ8P8AUP7Q8N+HfE2n/wDCEeGZbf8Atjwp4g/tP/hG/iPrvxF/sq/k0/U0yAfi1/wXH/bRsPiv8R9L/ZQ8CTfaPCPwP8TTa58Rdbt77w5quleJ/irLoUdhY6XpUunw3+p6d/wqzTNa8T+GfEEc2t6fNdeMtd8TaDrvhW2uPAuj6vqIB+B1AH9Mn/Bup/zeH/3b7/726gA/4OK/+bPP+7gv/eI0AfzN0Afvj/wQ4/bRsPhR8R9U/ZQ8dzfZ/CPxw8TQ658OtbuL7w5pWleGPirFoUlhfaXqsuoQ2Gp6j/wtPTNF8MeGfD8cOt6hNa+MtC8M6DoXhW5uPHWsavpwB+0v/BV39kr/AIaq/ZU8S/8ACM6L/afxc+D/ANr+Jfwy+w6b9t13WP7Ms3/4TP4f6f8A2f4b8ReJtQ/4TfwzFcf2P4U8P/2Z/wAJJ8R9C+HX9q38en6Y+AD+GmgAoAKACgAoAKACgD0z4K/EX/hT/wAZPhL8W/7H/wCEi/4Vb8TfAfxF/wCEf/tD+yP7d/4QjxTpXib+x/7V+w6n/Zn9p/2Z9i/tD+zdQ+xef9p+w3flfZ5AD+rH9oz/AILd/sx2H7PGvav+z14r8TeIfjv4s8MppfhDwpeeCdX0G/8Ahf4j8SaJcEeKvGOp+K/DWpfD7Vf+FcXbCW60Pw7f+ObDxT4jttO0S1km8Kajqfi/SAD+RXW9b1nxLrOr+I/Eer6nr/iHX9Tv9b13Xdbv7rVdZ1vWdVupb7VNX1fVL6We+1LU9Svp57y/v7yea6vLqaW4uJZJpHdgDMoA/pk/4N1P+bw/+7ff/e3UAH/BxX/zZ5/3cF/7xGgD+ZugDT0TW9Z8NazpHiPw5q+p6B4h0DU7DW9C13RL+60rWdE1nSrqK+0vV9I1SxlgvtN1PTb6CC8sL+znhurO6hiuLeWOaNHUA/rq/Zz/AOC3f7Md/wDs8aDq/wC0L4r8TeHvjv4T8Mvpfi/wpZ+CdX16/wDih4j8N6Jbk+KvB2p+FPDWm/D7Sv8AhY92pltdD8RX/gaw8LeI7nUdEupIfCmnaZ4v1cA/lO+NXxF/4XB8ZPi18W/7H/4R3/haXxN8efEX/hH/AO0P7X/sL/hN/FOq+Jv7H/tX7Dpn9p/2Z/af2L+0P7N0/wC2+R9p+w2nm/Z4wDzOgAoAKACgAoAKACgAoAKACgD9kv8Agjz+3P8AB/8AY88efFrw58bjqeg+C/jHpnhCVPiLYWWp69a+D9Z+HMfjS5sbDW/DGhaVqfiG/wBM8VQ+Lruzi1fQ4NQutH1mx0i3vNEn0bWNU8QeGAA/4LDftz/B/wDbD8efCXw58ETqeveC/g5pni+V/iLf2Wp6Da+MNZ+I0fgu5vrDRPDGu6VpniGw0zwrD4RtLOXV9cg0+61jWb7V7ez0SDRtH0vxB4nAPxtoAKACgAoAKACgAoA//9k=");
    }
    
    /**
     * 图片转化成base64字符串
     */
    public static String GetImageStr()
    { 
        String imgFile = "d://11.jpg";
        InputStream in = null;
        byte[] data = null;
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
    
    /**
     * Base64字符串转化成图片
     */
    public static String GenerateImage(HttpServletRequest request, String imgStr, String diskPath)
    {   
        if(imgStr == null || imgStr.equals(""))
        {
        	return "";
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try 
        {
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {
                    b[i]+=256;
                }
            }
            String basePath = request.getSession().getServletContext().getRealPath("/");
			String webPath = diskPath+"/"+UUID.randomUUID()+".jpg";
			String fullName = basePath + webPath;
			File file = new File(basePath + diskPath);
			if(!file.exists() && !file.isDirectory())      
			{       
			    file.mkdir();    
			} 
            OutputStream out = new FileOutputStream(fullName);    
            out.write(b);
            out.flush();
            out.close();
            return webPath;
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
            return "";
        }
    }
}

