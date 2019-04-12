package superapp.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NostalgieConvertingService implements ConvertingService {

    private final double rate;

    public NostalgieConvertingService( @Value("30") double rate) {
        this.rate = rate;
    }

    @Override
    public double convert(double from) {
        return rate*from;
    }
}
