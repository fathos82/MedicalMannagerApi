package med.vol.api.domain.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public  class  DataConverter implements IDataConverter{
    private  ObjectMapper mapper;
    @Override
    public <T> T convert(String source, Class<T> targetClass) {
        try {
            return mapper.readValue(source, targetClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
