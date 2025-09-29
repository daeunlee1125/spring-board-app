package kr.co.sboard.service;

import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.entity.Terms;
import kr.co.sboard.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TermsService {
    private final TermsRepository termsRepository;

    public void save(TermsDTO termsDTO) {}
    public void modify(TermsDTO termsDTO){}
    public TermsDTO getTerms(int no){
        Optional<Terms> terms = termsRepository.findById(no);
        if (terms.isPresent()){
            TermsDTO termsDTO = terms.get().toDTO();
            return termsDTO;
        }
        return null;
    }
    public List<TermsDTO> getTermsAll(){

        return null;
    }
    public void remove(int no){}

}
