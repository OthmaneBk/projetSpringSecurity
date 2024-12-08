export default class Personne{
    constructor(nomutilisateur,motdepasse,roleuti){
        this._nomutilisateur=nomutilisateur;
        this._motdepasse=motdepasse;
        this._roleuti=roleuti;
    }

    get nomutilisateur(){return this._nomutilisateur}
    get motdepasse(){return this.motdepasse}
    get roleuti(){return this.roleuti}


    set nomutilisateur(value){this._nomutilisateur=value;}
    set motdepasse(value){this._motdepasse=value;}
    set roleuti(value){this._roleuti=value;}
}