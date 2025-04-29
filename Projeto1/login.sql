/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  luiz.souza
 * Created: 28 de abr. de 2025
 */

-- estudos.login definição

CREATE TABLE `login` (
  `nomeUsuario` varchar(100) NOT NULL,
  `senhaUsuario` varchar(100) NOT NULL,
  `acessoUsuario` varchar(100) DEFAULT NULL,
  `dataCadastro` datetime DEFAULT NULL,
  `dataUltimoAcesso` datetime DEFAULT NULL,
  `grupoUsuarios` varchar(100) DEFAULT NULL,
  `nomeCompletoUsuario` varchar(100) NOT NULL,
  PRIMARY KEY (`nomeUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;