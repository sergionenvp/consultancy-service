package builders;

import assets.Assets;

import java.util.ArrayList;
import java.util.HashSet;

public class ConsultancyAssetsBuilder {

    private int noOfElements;
    private ArrayList<Assets> consultancyAssets = new ArrayList<>();


    public ConsultancyAssetsBuilder addListOfAssets(ArrayList<Assets> consultancyAssets){
        this.consultancyAssets = consultancyAssets;
        return this;
    }

    public ConsultancyAssetsBuilder addAsset(Assets asset){
        this.consultancyAssets.add(asset);
        return this;
    }


    public ArrayList<Assets> buildAssetList(){
        checkForDuplicateAssets(consultancyAssets);
        return this.consultancyAssets;
    }

    //checks if the same consultant or consultancy package was included in the list
    public void checkForDuplicateAssets(ArrayList<Assets> assetsList) {
        HashSet<Assets> set = new HashSet<>();

        for( Assets asset: assetsList){
            if (!set.contains(asset) ) {
                set.add(asset);
            }else{
                throw new IllegalStateException("Duplicate consultancy assets listed");
            }
        }

    }
}
