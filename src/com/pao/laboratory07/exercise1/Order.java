package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.CannotCancelFinalOrderException;
import com.pao.laboratory07.exercise1.exceptions.CannotRevertInitialOrderStateException;
import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;

import java.util.Stack;

public class Order {
    private StareComanda stare_comanda;
    private Stack<StareComanda> stiva;

    public Order(StareComanda a) {
        this.stare_comanda=a;
        stiva= new Stack<>();
        stiva.push(a);
    }

    public void cancel() {
        if (stare_finala()){
            throw new CannotCancelFinalOrderException("");
        }
        this.stare_comanda=StareComanda.CANCELED;
        stiva.push(stare_comanda);
        System.out.println("Order has been canceled.");
    }

    public void nextState() {
        if(stare_finala()){
            throw new OrderIsAlreadyFinalException("");
        }
        StareComanda urmatoare = null;
        switch(this.stare_comanda){
            case PLACED -> urmatoare=StareComanda.PROCESSED;
            case PROCESSED -> urmatoare=StareComanda.SHIPPED;
            case SHIPPED -> urmatoare=StareComanda.DELIVERED;
        }
        this.stare_comanda=urmatoare;
        stiva.push(stare_comanda);

        System.out.println("Order state updated to: "+this.stare_comanda.toString());


    }

    public void undoState() {
        if(stiva.size()<=1){
            throw new CannotRevertInitialOrderStateException("");
        }

        stiva.pop();
        stare_comanda=stiva.peek();
        System.out.println("Order state reverted to: "+this.stare_comanda.toString());
    }

    private boolean stare_finala(){
        return stare_comanda == StareComanda.DELIVERED || stare_comanda == StareComanda.CANCELED;
    }

}
