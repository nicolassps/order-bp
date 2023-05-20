package br.com.orderbp.domain.port;

import br.com.orderbp.domain.port.dto.ChangeOrderStateDTO;

public interface ChangeOrderStatePort {

    void execute(ChangeOrderStateDTO changeOrderStateDTO);

}
