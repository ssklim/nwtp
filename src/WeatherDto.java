import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherDto {
    private Response response;

    @Data
    @AllArgsConstructor
    class Response {
        private Header header;
        private Body body;

        @Data
        @AllArgsConstructor
        class Header {
            String resultCode;
            String resultMsg;
        }

        @Data
        @AllArgsConstructor
        class Body {
            private String dataType;
            private int pageNo;
            private int totalCount;
            private int numOfRows;
            private Items items;

            @Data
            @AllArgsConstructor
            class Items {
                private List<Item> item;
            }
        }
    }
}

@Data
@AllArgsConstructor
class Item {
    private String baseDate;
    private String baseTime;
    private String category;
    private int nx;
    private int ny;
    private String obsrValue;
}