package xyz.xingwei.fibee.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * @author xingwei.xyz
 * @date 2021/5/2 21:20
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvider {

    private Object provider;

    private String version = "";

    private String group = "";

    public String getServiceName() {
        return provider.getClass().getInterfaces()[0].getCanonicalName() + group + version;
    }
}
