module alu(S0, S1, D0, D1, O0);
    input S0, S1, D0, D1;
    output O0;
    
    assign W0 = ~S0 & ~S1;
    assign W1 = ~S0 & S1;
    assign W2 = S0 & ~S1;
    assign W3 = S0 & S1;
    
    assign V0 = W0 & (D0 & D1);
    assign V1 = W1 & (D0 | D1);
    assign V2 = W2 & (D0 ^ D1);
    assign V3 = W3 & ~D0;
    
    assign O0 = V0 | V1 | V2 | V3;
endmodule

module TB;
    reg s0, s1, d0, d1;
    wire O0;
    alu ALU(s0, s1, d0, d1, O0);
    
    initial begin
    $display("s0  s1  d0  d1  out");
    $display("-----------------------");
    s0 = 0; s1 = 0; d0 = 0; d1 = 0;
    #1 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 0; d0 = 0; d1 = 1;
    #2 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 0; d0 = 1; d1 = 0;
    #3 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 0; d0 = 1; d1 = 1;
    #4 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 1; d0 = 0; d1 = 0;
    #5 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 1; d0 = 0; d1 = 1;
    #6 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 1; d0 = 1; d1 = 0;
    #7 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 0; s1 = 1; d0 = 1; d1 = 1;
    #8 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 0; d0 = 0; d1 = 0;
    #9 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 0; d0 = 0; d1 = 1;
    #10 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 0; d0 = 1; d1 = 0;
    #11 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 0; d0 = 1; d1 = 1;
    #12 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 1; d0 = 0; d1 = 0;
    #13 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 1; d0 = 0; d1 = 1;
    #14 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 1; d0 = 1; d1 = 0;
    #15 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    s0 = 1; s1 = 1; d0 = 1; d1 = 1;
    #16 $display("%b | %b | %b | %b |  %b", s0, s1, d0, d1, O0);
    end
endmodule