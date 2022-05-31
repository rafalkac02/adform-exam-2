import Main._
import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers._

class MainSpec extends AnyFlatSpec with should.Matchers {

  behavior of "isPositiveNumber"
  it should "check if string is a number" in {

    isPositiveNumber("10") shouldBe true
    isPositiveNumber("123456") shouldBe true
    isPositiveNumber("Scala") shouldBe false
    isPositiveNumber("-5") shouldBe false
  }

  behavior of "isNegativeNumber"
  it should "check if string is a negative number" in {
    isNegativeNumber("-10") shouldBe true
    isNegativeNumber("-321") shouldBe true
    isNegativeNumber("44") shouldBe false
    isNegativeNumber("Hi") shouldBe false
  }

  behavior of "convertText()"
  it should "change words in text according to following rules:" +
    "\n1. multiply positive numbers by given parameter" +
    "\n2. add 'negative number: ' prefix to negative numbers" +
    "\n3. uppercase other words" in {

    val text1 = "Hi, good luck on the exam. It is 5 degrees"
    convertText(text1, 3) shouldBe "HI, GOOD LUCK ON THE EXAM. IT IS 15 DEGREES"


    val text2 = "-5 is your score haha"
    convertText(text2, 1) shouldBe "negative number: -5 IS YOUR SCORE HAHA"

    val text3 = "i was 5 yo now i am 50"
    convertText(text3, 4) shouldBe "I WAS 20 YO NOW I AM 200"
  }
}