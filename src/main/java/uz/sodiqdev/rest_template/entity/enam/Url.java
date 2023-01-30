package uz.sodiqdev.rest_template.entity.enam;

public enum Url {
    LOCAL(""),
    CBU("https://cbu.uz/uz/arkhiv-kursov-valyut/json/"),
    YANDEX("");

    String label;

    Url(String s) {
        label = s;
    }

    public String getUrl(){
        return label;
    }


}
