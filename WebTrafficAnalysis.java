import java.util.*;

public class WebTrafficAnalysis {
    public static void main(String[] args) {
        
        String[] cities = {"Pune", "Nanded", "AhamadNagar", "Hingoli", "Parbhani"};

      
        List<VisitEntry> visitEntries = generateWebTrafficData(cities);

     
        analyzeWebTrafficData(visitEntries, cities);
    }

 
    static class VisitEntry {
        String city;
        int userId;
        String timeOfDay;
        int timeSpentMinutes;

        VisitEntry(String city, int userId, String timeOfDay, int timeSpentMinutes) {
            this.city = city;
            this.userId = userId;
            this.timeOfDay = timeOfDay;
            this.timeSpentMinutes = timeSpentMinutes;
        }
    }

 
    static List<VisitEntry> generateWebTrafficData(String[] cities) {
        List<VisitEntry> visitEntries = new ArrayList<>();

      
        for (String city : cities) {
            for (int i = 0; i < 100; i++) {
                int userId = (int) (Math.random() * 30) + 1;
                String timeOfDay = getRandomTimeOfDay();
                int timeSpentMinutes = (int) (Math.random() * 60) + 1;

                visitEntries.add(new VisitEntry(city, userId, timeOfDay, timeSpentMinutes));
            }
        }

        return visitEntries;
    }

  
    static String getRandomTimeOfDay() {
        String[] timeOfDayOptions = {"morning", "noon", "evening", "night"};
        int randomIndex = (int) (Math.random() * timeOfDayOptions.length);
        return timeOfDayOptions[randomIndex];
    }

   
    static void analyzeWebTrafficData(List<VisitEntry> visitEntries, String[] cities) {
        Map<String, Integer> timeOfDayCount = new HashMap<>();
        Map<String, Map<String, Integer>> cityTimeOfDayCount = new HashMap<>();

   
        for (VisitEntry entry : visitEntries) {
    
            timeOfDayCount.put(entry.timeOfDay, timeOfDayCount.getOrDefault(entry.timeOfDay, 0) + 1);

       
            if (!cityTimeOfDayCount.containsKey(entry.city)) {
                cityTimeOfDayCount.put(entry.city, new HashMap<>());
            }
            Map<String, Integer> cityCount = cityTimeOfDayCount.get(entry.city);
            cityCount.put(entry.timeOfDay, cityCount.getOrDefault(entry.timeOfDay, 0) + 1);
        }

      
        String mostUsedTimeOfDay = Collections.max(timeOfDayCount.entrySet(), Map.Entry.comparingByValue()).getKey();
        System.out.println("1. Time of day with the most unique users: " + mostUsedTimeOfDay);

int count = 0 ;
String citytt ="";
String timestamp ="" ;
     
        for (String city : cities) {
            Map<String, Integer> cityCount = cityTimeOfDayCount.get(city);
            if (cityCount != null) {
                String mostUsedCityTimeOfDay = Collections.max(cityCount.entrySet(), Map.Entry.comparingByValue()).getKey();
                int usersCount = cityCount.get(mostUsedCityTimeOfDay);
               if(count < usersCount){
                   count = usersCount;
                   citytt = city ;
                   timestamp = mostUsedCityTimeOfDay;
               }
            }
        }
         System.out.println("2. " + citytt + " " + timestamp + " " + count + " users");
     }
}
