package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class ReadWriteExcelProperties {

    static Map<String, String> properties = null;

    static String[][] data = null;

    public void readWriteProperties() {

        ReadWriteExcelProperties readWriteExcelProperties = new ReadWriteExcelProperties();

        ReadExcel read = new ReadExcel();

        data=read.getSheet("datasheet.xlsx","browserProperties");

        properties = new HashMap<String, String>(data.length);
        for (String[] mapping : data)
        {
            properties.put(mapping[0], mapping[1]);
        }

        readWriteExcelProperties.writeToPropertiesFile("./src/main/resources/resources.properties");

    }


    public void writeToPropertiesFile(String propertiesPath) {
        Properties props = new Properties();

        File propertiesFile = new File(propertiesPath);

        try {
            FileOutputStream xlsFos = new FileOutputStream(propertiesFile);

            Iterator mapIterator = properties.keySet().iterator();

            while(mapIterator.hasNext()) {
                String key = mapIterator.next().toString();

                String value = properties.get(key);

                props.setProperty(key, value);

            }

            props.store(xlsFos, null);

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}

