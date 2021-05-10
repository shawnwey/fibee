package xyz.xingwei.fibee.test;

/**
 * @author xingwei.xyz
 * @date 2021/4/30 20:04
 */
public class PriceServiceImpl implements IPriceService{
    @Override
    public Double getPrice(Double originalPrice, Float discount) {
        return originalPrice * discount;
    }
}
