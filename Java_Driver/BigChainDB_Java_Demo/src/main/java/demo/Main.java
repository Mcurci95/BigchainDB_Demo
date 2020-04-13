package demo;


import utils.WeatherFileReader;

public class Main {

    public static void main(String[] args) throws Exception {
//        DemoDriver demo = new DemoDriver();
////
////        demo.run();;

        
        // Weather file
        WeatherFileReader weatherFileReader = new WeatherFileReader();
        weatherFileReader.ReadFile("src/main/java/resources/01002099999.csv");

    }
}
