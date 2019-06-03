package model;



import javax.persistence.*;
import java.awt.*;
import java.util.Arrays;

@Entity
@Table(name="Encoder")
public class Encoder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  public int getId() {
    return id;
  }

  public int getEncodeValue() {
    return encodeValue;
  }

  public Encoder(int encodeValue) {

    this.encodeValue = encodeValue;
  }

  public Encoder() {
  }

  private int encodeValue;

  public int encodePin(int pin){
    Point numbers = getSimpleNumbers(pin);

    int n = numbers.x*numbers.y;

    int result = (int)Math.pow(pin,encodeValue)%n;

    return result;
  }

  private Point getSimpleNumbers(int N){
    Point result = new Point(0,0);
    boolean[] isPrime = new boolean[N];
    Arrays.fill(isPrime,true);
    isPrime[1] = false;
    for (int i=2; i*i < N; i++)
      if (isPrime[i])
        for (int j=i*i; j < N; j+=i)
          isPrime[j] = false;

        for(int i = N-1;i>0;i--){
          if(isPrime[i]&&result.x==0)
            result.x=i;
          else if(isPrime[i]){
            result.y=i;
            return result;
          }
        }
        return result;
  }
}
