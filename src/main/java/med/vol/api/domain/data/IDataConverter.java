package med.vol.api.domain.data;

public interface IDataConverter {
    <T> T convert(String source, Class<T> targetClass);

}
