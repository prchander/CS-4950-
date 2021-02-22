package HW2;
//GCD with Euclidean Algorithm

import java.awt.desktop.ScreenSleepEvent;

public class EuclideanALg {
    public static void main(String[] args) {
        System.out.println("Answers for problem 5");
        System.out.println("problem 5 a");
        int pFivea[]={2105,425};
        int tGcd[] = toMod(pFivea);
        System.out.println(findGcd(tGcd));
        System.out.println("problem 5 b");
        int pFiveb[]={3555,12075};
        tGcd = toMod(pFiveb);
        System.out.println(findGcd(tGcd));
        System.out.println("problem 5 c");
        int pFivec[]={2078,9602};
        tGcd = toMod(pFivec);
        System.out.println(findGcd(tGcd));
        System.out.println("problem 5 d");
        int pFived[]={24142,16762};
        tGcd = toMod(pFived);
        System.out.println(findGcd(tGcd));


    }
    public static int[] toMod(int tMod[]) {
        //check is array[0] is the largest and if not change order
        if (tMod[0]<tMod[1]){
            int temp = tMod[0];
            int temp1 = tMod[1];
            tMod[0] = temp1;
            tMod [1] = temp;
        }
        return tMod;
    }
    //this is where the gcd is calculated
    public static int findGcd(int fGcd[])
    {
        int gcd=0;
        int temp=1;
        while (fGcd[1]!=0)
        {
            temp=fGcd[0]%fGcd[1];
            if (temp != 0) {
                fGcd[0]=fGcd[1];
                fGcd[1]=temp;
            }
            else {
                gcd = fGcd[0]/fGcd[1];
                fGcd[1] = temp;
            }
        }
        return gcd;
    }
}