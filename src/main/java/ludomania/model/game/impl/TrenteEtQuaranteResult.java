package ludomania.model.game.impl;

import ludomania.model.bet.impl.TrenteEtQuaranteBetColor;
import ludomania.model.bet.impl.TrenteEtQuaranteBetType;
import ludomania.model.game.api.CounterResult;
import javafx.util.Pair;

public class TrenteEtQuaranteResult extends CounterResult<Pair<TrenteEtQuaranteBetColor,TrenteEtQuaranteBetType>>{

    public TrenteEtQuaranteResult(Pair<TrenteEtQuaranteBetColor, TrenteEtQuaranteBetType> result) {
        super(result);
    }

    public TrenteEtQuaranteBetColor getColor(){
        return result.getKey();
    }

    public TrenteEtQuaranteBetType getType(){
        return result.getValue();
    }
    
}
