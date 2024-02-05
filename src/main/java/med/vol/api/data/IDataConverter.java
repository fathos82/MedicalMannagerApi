package med.vol.api.data;

public interface IDataConverter {
    <T> T convert(String source, Class<T> targetClass);

}
