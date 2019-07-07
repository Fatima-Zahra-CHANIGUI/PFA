#include <stdio.h>

#define OUT1 12
#define OUT2 13
#define OUT3 11
#define OUT4 10
#define SP1 5
#define SP2 6

#define ORDER_COMPLETED 8
#define V 7

#define IN1 1
#define IN2 2
#define IN3 3

int valOrder;

int val1;
int val2;
int val3;
int v;

void setup()
{
    pinMode(ORDER_COMPLETED,INPUT);
    pinMode(V,INPUT);
    pinMode(OUT1, OUTPUT);
    pinMode(OUT2, OUTPUT);
    pinMode(OUT3, OUTPUT);
    pinMode(OUT4, OUTPUT);
    pinMode(SP1, OUTPUT);
    pinMode(SP2, OUTPUT);
    Serial.begin(9600);
    
}

void goForward()
{
    analogWrite(SP1, 255);
    analogWrite(SP2, 255);
    digitalWrite(OUT1, HIGH);
    digitalWrite(OUT2, LOW);
    digitalWrite(OUT3, HIGH);
    digitalWrite(OUT4, LOW);
    Serial.println("go FORWARD");
    
    
}
void goLeft()
{   
    analogWrite(SP1, 255);
    analogWrite(SP2, 255);
    digitalWrite(OUT1, LOW);
    digitalWrite(OUT2, HIGH);
    digitalWrite(OUT3, HIGH);
    digitalWrite(OUT4, LOW);
    Serial.println("go XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
    
    
}
void goRight()
{
    analogWrite(SP1, 255);
    analogWrite(SP2, 255);
    digitalWrite(OUT1, HIGH);
    digitalWrite(OUT2, LOW);
    digitalWrite(OUT3, LOW);
    digitalWrite(OUT4, HIGH);
    Serial.println("go RIGHT");
}
void goBack()
{
    analogWrite(SP1, 255);
    analogWrite(SP2, 255);
    digitalWrite(OUT1, LOW);
    digitalWrite(OUT2, HIGH);
    digitalWrite(OUT3, LOW);
    digitalWrite(OUT4, HIGH);
    Serial.println("go BACK");
}
void stp()
{
    digitalWrite(OUT1, LOW);
    digitalWrite(OUT3, LOW);
    digitalWrite(OUT2, LOW);
    digitalWrite(OUT4, LOW);
    analogWrite(SP1, 0);
    analogWrite(SP2, 0);
    Serial.println("Stop"); 
}

void loop()
{

  valOrder = digitalRead(ORDER_COMPLETED);
  if(valOrder == HIGH){
  
    val1 = digitalRead(IN1);
    val2 = digitalRead(IN2);
    val3 = digitalRead(IN3);
    v = digitalRead(V);

   if (val1 == LOW && val2 == HIGH && val3 == LOW)
        goForward();
    else if (val1 == HIGH && val2 == HIGH && val3 == HIGH)
        goBack();
    else if (v == HIGH)
        goLeft();
    else if (val1 == LOW && val2 == LOW && val3 == HIGH)
        goRight();
    else if (val1 == LOW && val2 == LOW && val3 == LOW)
        stp();
  }
}
