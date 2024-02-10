package com.github.juhachmann.estagios.core.entities;

import com.github.juhachmann.estagios.core.exceptions.UnauthorizedAccessException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

// Pedido para ver perfil
// Vai entrar aqui, o handler vai validar e vai pedir pro banco de dados enviar o perfil do usuário

// Pedido pra um usuário ver o perfil de outro
// vai entrar aqui, não precisa carregar todos os dados do usuário
// mas precisa dar um jeito de que no banco de dados ele veja se fornece vaga para alguma pessoa



public abstract class User {

    protected Long id;
    protected Boolean ie;


    public Boolean getIsIe() {
        return this.ie;
    }
    public Long getId() {
        return this.id;
    }


    public Vaga create (@NotNull Vaga vaga , @NotNull List<User> receivers ) {



        vaga.setOwnerId(this.id);

        // O fato de o usuário só poder receber uma vaga se for uma empresa, é uma regra de negócio e precisa ser validado aqui


        receivers.forEach(user -> { // TODO Era pra aceitar a ação, mas tbm mandar mensagem de erro, como faz isso?
            if( user.getIsIe() )  {
                if (!vaga.isOfferedTo ((Receiver)user))
                    vaga.addRecipients((Receiver)user);
                if (!this.offersVagasTo.contains((Receiver) user))
                    this.offersVagasTo.add((Receiver)user);
            }
        });
        return vaga;
    }

    public Vaga update ( Vaga vaga ) {
        if ( canManageVaga(vaga) ) {
            return vaga;
        }
        return null;
    }

    public Vaga delete ( Vaga vaga ) {
        if ( canManageVaga(vaga) ) {
            return vaga;
        }
        return null;
    }

    public Vaga showVagaPrivateView ( Vaga vaga ) throws UnauthorizedAccessException {
        if (canAccessVagaPrivateView(vaga)) {
            return vaga;
        }
        throw new UnauthorizedAccessException();
    }

    public Vaga showVagaPublicView ( Vaga vaga ) throws UnauthorizedAccessException {
        if (canAccessPublicViewOf(vaga)) {
            return vaga;
        }
        throw new UnauthorizedAccessException();
    }

    public User showSelfProfile(  ) {
        return this;
    }

    public void showSelfConfigs() {
        System.out.println(this);
    }

    public User showProfile(User user) throws UnauthorizedAccessException {
        if (canAccessPublicProfileOf(user)) {
            return user;
        } else throw new UnauthorizedAccessException();
    }

    protected Boolean offersVagaFor( User user ) {
        if ( Receiver.class.isAssignableFrom(user.getClass()) ) {
            return this.offersVagasTo.contains((Receiver) user);
        }
        return false;
    }

    protected Boolean isSelf(User user) {
        return this.id.equals(user.getId());
    }

    protected Boolean isOwner(Vaga vaga) {
        return this.id.equals(vaga.getOwnerId());
    }

    private Boolean canManageVaga(Vaga vaga) {
        return this.isOwner(vaga) ;
    }

    private Boolean canAccessVagaPrivateView (Vaga vaga) {
        return this.isOwner(vaga);
    }

    protected abstract Boolean canAccessPublicProfileOf(User user);

    protected abstract Boolean canAccessPublicViewOf(Vaga vaga);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(isIE, user.isIE) && Objects.equals(offersVagasTo, user.offersVagasTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
