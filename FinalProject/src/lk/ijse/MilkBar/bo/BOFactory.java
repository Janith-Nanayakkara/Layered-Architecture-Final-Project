package lk.ijse.MilkBar.bo;

import lk.ijse.MilkBar.bo.custom.SuperBO;
import lk.ijse.MilkBar.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOType types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            case PO:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}
